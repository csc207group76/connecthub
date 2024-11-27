package controller.delete_post;

import use_case.delete_post.DeletePostInputBoundary;
import use_case.delete_post.DeletePostInputData;

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
     * @param userId the ID of the user attempting to delete the post
     */
    public void deletePost(String postId, String userId) {
        final DeletePostInputData deletePostInputData = new DeletePostInputData(postId, userId);
        deletePostUseCaseInteractor.deletePost(deletePostInputData);
    }
}
