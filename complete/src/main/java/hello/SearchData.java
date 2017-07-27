package hello;

public class SearchData {

    private long id;
    private String searchString;
    private QuestionData[] questions;
    private String answers;

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

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}