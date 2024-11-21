package use_case.createPost;

import entity.Comment;
import entity.User;
import entity.PostContent;

import java.time.LocalDateTime;
import java.util.List;


public class CreatePostInputData {

    private final String entryID;
    private final String author;
    private final PostContent content;
    private final LocalDateTime timestamp;
    private final LocalDateTime lastModified;
    private final int dislikes;
    private final int likes;
    private final String postTitle;
    private final List<User> moderators;
    private final List<Comment> comments;
    private final String category;


    public CreatePostInputData(String entryID, String author, PostContent content, LocalDateTime timestamp, LocalDateTime lastModified, int dislikes, int likes, String postTitle, List<User> moderators, List<Comment> comments, String category) {
        this.entryID = entryID;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.lastModified = lastModified;
        this.dislikes = dislikes;
        this.likes = likes;
        this.postTitle = postTitle;
        this.moderators = moderators;
        this.comments = comments;
        this.category = category;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }
}