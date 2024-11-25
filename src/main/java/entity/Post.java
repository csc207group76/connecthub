package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents a post.
 */
public class Post extends ForumEntry {
    private String postTitle;
    private List<Comment> comments;
    private String category;

    /**
     * Creates a post.
     * @param postTitle - the title of the post.
     * @param comments - the comments under this post.
     * @param category - the category of this post.
     * See ForumEntry for other parameters
     */
    public Post(String entryID, String author, Content content, LocalDateTime postedDate,
                LocalDateTime lastModifiedDate, int likes, int dislikes, String postTitle,
                List<Comment> comments, String category) {
        super(entryID, author, content, postedDate, lastModifiedDate, likes, dislikes);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Post post = (Post) obj;
        return Objects.equals(postTitle, post.postTitle) &&
                Objects.equals(comments, post.comments) &&
                Objects.equals(category, post.category) &&
                super.equals(obj); // Include ForumEntry fields in comparison
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), postTitle, comments, category);
    }
}
