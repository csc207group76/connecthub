package controller.createComment;

import controller.post.PostState;

import entity.User;

public class CreateCommentState {

    private String entryID = "";
    private String entryIDError = "";

    private User author = null;
    private String authorError = "";

    private String content;
    private String contentError = "";

    private PostState postState = null;

    private String commentDate = "";

    private String attchmentPath = "";

    private String filePath = "";

    public PostState getPostState(){return postState;}

    public void setPostState(){this.postState = postState;}

    public String getEntryID() {
        return entryID;
    }

    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public String getEntryIDError() {
        return entryIDError;
    }

    public void setEntryIDError(String entryIDError) {
        this.entryIDError = entryIDError;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorError() {
        return authorError;
    }

    public void setAuthorError(String authorError) {
        this.authorError = authorError;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }


    public String getAttchmentPath() {
        return attchmentPath;
    }

    public void setAttchmentPath(String attchmentPath) {
        this.attchmentPath = attchmentPath;
    }

    public String getFilepath() {
        return filePath;
    }

    public void setFilepath(String filepath) {
        this.filePath = filepath;
    }
}
