package controller.create_post;

import entity.Comment;
import entity.Content;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class CreatePostState{

    private String entryID = "";
    private String entryIDError = "";

    private String author = "";
    private String authorError = "";

    private Content content;
    private String contentError = "";

    // Since these are generated using the database I thought they woudln't be necessary

//    private LocalDateTime timestamp = LocalDateTime.now();
//    private String timestampError = "";
//
//    private LocalDateTime lastModified = LocalDateTime.now();
//    private String lastModifiedError = "";

    private int dislikes = 0;
    private String dislikesError = "";

    private int likes = 0;
    private String likesError = "";

    private String postTitle = "";
    private String postTitleError = "";

    private List<User> moderators = new ArrayList<>();
    private String moderatorsError = "";

    private List<Comment> comments = new ArrayList<>();
    private String commentsError = "";

    private String category = "";
    private String categoryError = "";


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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorError() {
        return authorError;
    }

    public void setAuthorError(String authorError) {
        this.authorError = authorError;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }



    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getDislikesError() {
        return dislikesError;
    }

    public void setDislikesError(String dislikesError) {
        this.dislikesError = dislikesError;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getLikesError() {
        return likesError;
    }

    public void setLikesError(String likesError) {
        this.likesError = likesError;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTitleError() {
        return postTitleError;
    }

    public void setPostTitleError(String postTitleError) {
        this.postTitleError = postTitleError;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public String getModeratorsError() {
        return moderatorsError;
    }

    public void setModeratorsError(String moderatorsError) {
        this.moderatorsError = moderatorsError;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCommentsError() {
        return commentsError;
    }

    public void setCommentsError(String commentsError) {
        this.commentsError = commentsError;
    }

    public String getCategoryError() {
        return categoryError;
    }

    public void setCategoryError(String categoryError) {
        this.categoryError = categoryError;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CreatePost{" +
                "entryID='" + entryID + '\'' +
                ", author='" + author + '\'' +
                ", content=" + content +
                ", dislikes=" + dislikes +
                ", likes=" + likes +
                ", postTitle='" + postTitle + '\'' +
                ", moderators=" + moderators +
                ", comments=" + comments +
                ", category='" + category + '\'' +
                '}';
    }
}