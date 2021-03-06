package StackOverflowSearch;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.unbescape.html.HtmlEscape;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.GZIPInputStream;

@Controller
public class SOSearchController {

    @GetMapping("/SOSearch")
    public String searchForm(Model model) {
        model.addAttribute("searchData", new SearchData());
        return "input";
    }

    @PostMapping("/SOSearch")
    public String searchSubmit(@ModelAttribute SearchData searchData){
        if (searchData.getSearchString() == "")
            return "input";
        try {
            byte[] bytes = performRequest(searchData);
            String unzipped = unzip(bytes);
            JSONObject json = (JSONObject) JSONValue.parse(unzipped);
            ArrayList<QuestionData> answersList = extractQuestionData(json);
            searchData.setQuestions(answersList.toArray(new QuestionData[0]));
            if (searchData.getMaxVisitedPageNumber() <= searchData.getPageNumber()) {
                searchData.setMaxVisitedPageNumber(searchData.getPageNumber());
                searchData.setLastIsVisited(!(boolean)json.get("has_more"));
            }

            if (searchData.getSearchString() != null && searchData.getSearchString() != "" && (searchData.getQuestions() == null || searchData.getQuestions().length == 0))
                searchData.setErrorMessage("Oh snap! String ''" + searchData.getSearchString() + "'' not found in question titles. Try to correct it and search again.");
        }
        catch (IOException ex)
        {
            searchData.setErrorMessage(ex.getMessage());
        }
        return "input";
    }

    private ArrayList<QuestionData> extractQuestionData(JSONObject json) {
        ArrayList<QuestionData> answersList = new ArrayList();
        if (json.containsKey("items")){
            JSONArray items = (JSONArray) json.get("items");
            for (Object item1: items) {
                JSONObject item = (JSONObject) item1;
                QuestionData q = new QuestionData();
                JSONObject author = (JSONObject)item.get("owner");
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((Integer) item.get("creation_date") * 1000L));
                q.setDate(date);
                q.setAuthor(HtmlEscape.unescapeHtml((String)author.get("display_name")));
                q.setTitle(HtmlEscape.unescapeHtml((String)item.get("title")));
                q.setAnswered((boolean)item.get("is_answered"));
                q.setLink((String)item.get("link"));
                answersList.add(q);
            }
        }
        return answersList;
    }

    private byte[] performRequest(@ModelAttribute SearchData searchData) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity("", headers);
        String url = "http://api.stackexchange.com/2.2/search?key=U4DMV*8nvpm3EOpvf69Rxw((&site=stackoverflow&order=desc&sort=activity&intitle={searchString}&filter=default&pagesize=10&page={pageNumber}";
        ResponseEntity<byte[]> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, byte[].class, searchData.getSearchString(), searchData.getPageNumber());
        return responseEntity.getBody();
    }

    private String unzip(byte[] bytes) throws IOException {
        GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(bytes));
        StringBuffer szBuffer = new StringBuffer ();
        byte tByte [] = new byte [1024];
        while (true)
        {
            int  iLength = gzip.read (tByte, 0, 1024);

            if (iLength < 0)
                break;

            szBuffer.append (new String (tByte, 0, iLength));
        }
        return szBuffer.toString();
    }
}
