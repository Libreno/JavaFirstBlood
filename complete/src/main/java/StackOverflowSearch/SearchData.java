package StackOverflowSearch;

public class SearchData {

    private String searchString;
    private QuestionData[] questions;
    private Integer pageNumber;
    private boolean hasMore;

    public SearchData() {
        pageNumber = 1;
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