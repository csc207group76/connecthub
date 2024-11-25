package use_case.create_post;

import entity.Comment;
import entity.Content;
import entity.PostContent;

import java.time.LocalDateTime;
import java.util.List;

public class CreatePostOutputData {
    private String entryID;
    private String author;
    private PostContent content;
    private LocalDateTime postedDate;
    private LocalDateTime lastModifiedDate; 
    private int likes;
    private int dislikes;
    private final String postTitle;
    private final List<Comment> comments;
    private final String category;
    private final boolean creationSuccessful;

    public CreatePostOutputData(String entryID, String author, PostContent content, LocalDateTime postedDate,
                                LocalDateTime lastModified, int dislikes, int likes, String postTitle, 
                                List<Comment> comments, String category, boolean creationSuccessful) {
        this.entryID = entryID;
        this.author = author;
        this.content = content;
        this.postedDate = postedDate;
        this.lastModifiedDate = lastModified;
        this.dislikes = dislikes;
        this.likes = likes;
        this.postTitle = postTitle;
        this.comments = comments;
        this.category = category;
        this.creationSuccessful = creationSuccessful;
    }

    public String getEntryID() {
        return entryID;
    }

    public String getAuthor() {
        return author;
    }

    public PostContent getContent() {
        return content;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCreationSuccessful() {
        return creationSuccessful;
    }
}