package use_case.createPost;

import entity.Comment;
import entity.PostContent;
import entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class CreatePostOutputData {

    private final String entryID;
    private final String author;
    private final PostContent content;
    private final LocalDateTime timestamp;
    private final int dislikes;
    private final int likes;
    private final String postTitle;
    private final List<User> moderators;
    private final List<Comment> comments;
    private final String category;
    private final boolean creationSuccessful;

    public CreatePostOutputData(String entryID, String author, PostContent content, LocalDateTime timestamp, int dislikes, int likes, String postTitle, List<User> moderators, List<Comment> comments, String category, boolean creationSuccessful) {
        this.entryID = entryID;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.dislikes = dislikes;
        this.likes = likes;
        this.postTitle = postTitle;
        this.moderators = moderators;
        this.comments = comments;
        this.category = category;
//        this.author = author;
//        this.content = content;
//        this.timestamp = timestamp;
//        this.dislikes = dislikes;
//        this.likes = likes;
//        this.postTitle = postTitle;
//        this.moderators = moderators;
//        this.comments = comments;
//        this.category = category;
        this.creationSuccessful = creationSuccessful;
    }


    public String getEntryID() {
        return entryID;
    }

    public boolean isCreationSuccessful() {
        return creationSuccessful;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getDislikes() {
        return dislikes;
    }

    public PostContent getContent() {
        return content;
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
}