package controller.delete_post;

import use_case.delete_post.DeletePostInputBoundary;
import use_case.delete_post.DeletePostInputData;
import use_case.delete_post.DeletePostFailedException;

/**
 * Controller for the Delete Post Use Case.
 */
public class DeletePostController {

    private final DeletePostInputBoundary deletePostUseCaseInteractor;

    /**
     * Initializes the DeletePostController.
     *
     * @param deletePostUseCaseInteractor the interactor for the delete post use case
     */
    public DeletePostController(DeletePostInputBoundary deletePostUseCaseInteractor) {
        this.deletePostUseCaseInteractor = deletePostUseCaseInteractor;
    }

    /**
     * Executes the Delete Post Use Case.
     * @param postId the ID of the post to be deleted
     * @param currentUserId the ID of the author of the post
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deletePost(String postId, String currentUserId) {
        String authorId = deletePostUseCaseInteractor.getAuthorId(postId);
        try {
            final DeletePostInputData deletePostInputData = new DeletePostInputData(postId, currentUserId, authorId);
            deletePostUseCaseInteractor.deletePost(deletePostInputData);
            return true;
        } catch (DeletePostFailedException e) {
            System.err.println("Delete post failed: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error during post deletion: " + e.getMessage());
            return false;
        }
    }

    public void switchToHomePageView() {
        deletePostUseCaseInteractor.switchToHomePageView();
    }

}
