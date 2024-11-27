package controller.delete_post;

/**
 * The state for the Delete Post View Model.
 */
public class DeletePostState {
    private String postId = "";
    private String deleteError = "";
    private String successMessage = "This post has been deleted";

    public String getPostId() {
        return postId;
    }

    public String getDeleteError() {
        return deleteError;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setDeleteError(String deleteError) {
        this.deleteError = deleteError;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setPostID(String postId) {
    }
}
