package use_case.delete_post;

/**
 * Input data for the Delete Post Use Case.
 */
public class DeletePostInputData {

    private final String postId;
    private final String currentUserId;
    private final String authorId;

    public DeletePostInputData(String postId, String currentUserId, String authorId) {
        this.postId = postId;
        this.currentUserId = currentUserId;
        this.authorId = authorId;
    }

    public String getPostId() {
        return postId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public String getAuthorId() {
        return authorId;
    }
}
