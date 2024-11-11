package entity;

import java.time.LocalDateTime;

public class Post extends ForumEntry {
    private String postTitle;
    private Comment[] comments;
    private String category;

    public Post(String entryID, User author, Content content, LocalDateTime postedDate,
                LocalDateTime editDate, int likes, int dislikes, String postTitle, 
                Comment[] comments, String category) {
        super(entryID, author, content, postedDate, editDate, likes, dislikes);
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

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
