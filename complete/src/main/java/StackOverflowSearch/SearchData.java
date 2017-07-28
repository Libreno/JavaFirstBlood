package StackOverflowSearch;

public class SearchData {

    private String searchString;
    private QuestionData[] questions;
    private Integer pageNumber;
    private Integer maxVisitedPageNumber;
    private boolean lastIsVisited;
    private String errorMessage;

    public SearchData() {
        pageNumber = 1;
        maxVisitedPageNumber = 1;
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

    public boolean getLastIsVisited() {
        return lastIsVisited;
    }

    public void setLastIsVisited(boolean lastIsVisited) {
        this.lastIsVisited = lastIsVisited;
    }

    public Integer getMaxVisitedPageNumber() {
        return maxVisitedPageNumber;
    }

    public void setMaxVisitedPageNumber(Integer maxVisitedPageNumber) {
        this.maxVisitedPageNumber = maxVisitedPageNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}