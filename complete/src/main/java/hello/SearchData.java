package hello;

public class SearchData {

    private long id;
    private String searchString;
    private QuestionData[] questions;
    private Integer pageNumber;
    private boolean hasMore;

    public SearchData() {
        pageNumber = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public QuestionData[] getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionData[] questions) {
        this.questions = questions;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}