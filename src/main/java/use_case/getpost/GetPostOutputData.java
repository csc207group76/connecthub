package use_case.getpost;

import java.util.List;
import java.util.Objects;

import entity.Comment;
import entity.Content;
import entity.Post;

/**
 * The Input Data for the Get Post Use Case.
 */
public class GetPostOutputData {

    private final String entryID;
    private final String postTitle;
    private final Content postContent;
    private final List<Comment> comments;
    private final List<Post> allPosts;

    public GetPostOutputData(String entryID, String postTitle, Content postContent, List<Comment> comments) {
        this.entryID = entryID;
        this.postContent = postContent;
        this.postTitle = postTitle;
        this.comments = comments;
        this.allPosts = null;
    }

    public GetPostOutputData(List<Post> allPosts) {
        this.allPosts = allPosts;
        this.entryID = null;
        this.postTitle = null;
        this.postContent = null;
        this.comments = null;
    }

    public String getEntryID() {
        return entryID;
    }

    public Content getPostContent() {
        return postContent;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Post> getAllPosts() {
        return allPosts;
    }

    /**
     * Ensure that two posts are equal if they have the same content
     * This is so that testGetPost_PresenterCalledOnSuccess test passes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPostOutputData that = (GetPostOutputData) o;
        return entryID.equals(that.entryID) &&
                postContent.equals(that.postContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryID, postContent);
    }
}
