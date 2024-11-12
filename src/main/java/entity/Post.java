package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Post extends ForumEntry {
    private String postTitle;
    private List<Comment> comments;
    private String category;

    public Post(String entryID, User author, Content content, LocalDateTime postedDate,
                LocalDateTime getLastModifiedDate, int likes, int dislikes, String postTitle, 
                List<Comment> comments, String category) {
        super(entryID, author, content, postedDate, getLastModifiedDate, likes, dislikes);
        this.postTitle = postTitle;
        this.comments = comments;
        this.category = category;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
