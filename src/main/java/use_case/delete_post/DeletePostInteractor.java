package use_case.delete_post;

import daos.DBUserDataAccessObject;
import entity.User;

/**
 * Interactor for delete post.
 */
public class DeletePostInteractor implements DeletePostInputBoundary {

    private final DeletePostDataAccessInterface postDataAccessObject;
    private final DeletePostOutputBoundary postPresenter;
    private final DBUserDataAccessObject userRepo;

    public DeletePostInteractor(DeletePostDataAccessInterface postDataAccessObject,
                                DeletePostOutputBoundary postPresenter,
                                DBUserDataAccessObject userRepo) {
        this.postDataAccessObject = postDataAccessObject;
        this.postPresenter = postPresenter;
        this.userRepo = userRepo;
    }

    @Override
    public void deletePost(DeletePostInputData deletePostInputData) {
        if (!postDataAccessObject.existsByID(deletePostInputData.getPostId())) {
            postPresenter.prepareFailView("Post with given ID doesn't exist.");
            throw new DeletePostFailedException("Post with given ID doesn't exist.");
        }


        boolean userCanDelete = canDelete(deletePostInputData);
        if (!userCanDelete) {
            postPresenter.prepareFailView("User does not have permission to delete this post.");
            throw new DeletePostFailedException("User does not have permission to delete this post.");
        }

        try {
            postDataAccessObject.deletePost(deletePostInputData.getPostId());

            DeletePostOutputData outputData = new DeletePostOutputData(
                    deletePostInputData.getPostId(),
                    true
            );
            postPresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            postPresenter.prepareFailView("Failed to delete the post.");
            throw new DeletePostFailedException("Failed to delete the post.");
        }
    }

    public boolean canDelete(DeletePostInputData post) {
        final User currentUser = userRepo.getCurrentUser();
        return currentUser.getUserID().equals(post.getUserId()) ||
                currentUser.getModerating().contains(post.getPostId());
    }
}
