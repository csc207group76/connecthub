package controller.getpost;

import entity.Content;

public class GetPostViewModel {
    private String entryID;
    private Content postContent;
    private String errorMessage;

    // Getters and setters for UI to bind to
    public String getEntryID() {
        return entryID;
    }

    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public Content getPostContent() {
        return postContent;
    }

    public void setPostContent(Content postContent) {
        this.postContent = postContent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

