package use_case.delete_post;

/**
 * This class contains the output data for the deletion of a Post.
 *
 */
public class DeletePostOutputData {

    private final String PostId;
    private final boolean deletionSuccessful;

    /**
     * Constructs a DeletePostOutputData object with the specified Post ID
     * and the result of the deletion attempt.
     *
     * @param PostId The ID of the Post being deleted
     * @param deletionSuccessful A boolean indicating if the deletion operation Successful
     */
    public DeletePostOutputData(String PostId, boolean deletionSuccessful) {
        this.PostId = PostId;
        this.deletionSuccessful = deletionSuccessful;
    }

    /**
     * Returns whether the deletion of the Post Successful.
     *
     * @return true if the deletion Successful, false if it was successful
     */
    public boolean isDeletionSuccessful() {
        return deletionSuccessful;
    }

    /**
     * Returns the ID of the Post that was attempted to be deleted.
     *
     * @return the ID of the Post
     */
    public String getPostId() {
        return PostId;
    }
}