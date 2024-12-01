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

        if (!canDelete(deletePostInputData)) {
            postPresenter.prepareFailView("User does not have permission to delete this post.");
            throw new DeletePostFailedException("User does not have permission to delete this post.");
        }

        try {
            postDataAccessObject.deletePost(deletePostInputData.getPostId());
            userRepo.removePostFromUser(deletePostInputData.getCurrentUserId(), deletePostInputData.getPostId());

            final DeletePostOutputData outputData = new DeletePostOutputData(deletePostInputData.getPostId(), true);
            postPresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            postPresenter.prepareFailView("Failed to delete the post.");
            throw new DeletePostFailedException("Failed to delete the post.");
        }
    }

    /**
     * Check if the user can delete the post.
     * @param inputData the input data
     * @return true if the user can delete the post, false otherwise
     */
    public boolean canDelete(DeletePostInputData inputData) {
        User currentUser = userRepo.getCurrentUser();
        String currentUserId = inputData.getCurrentUserId();
        String authorId = inputData.getAuthorId();

        boolean isCreator = currentUserId.equals(authorId);
        boolean isModerator = currentUser.getModerating().contains(inputData.getPostId());

        return isCreator || isModerator;
    }

    @Override
    public void switchToHomePageView() {
        postPresenter.switchToHomePageView();
    }

    @Override
    public String getAuthorId(String postId) {
        return postDataAccessObject.getPostAuthorId(postId);
    }
}
