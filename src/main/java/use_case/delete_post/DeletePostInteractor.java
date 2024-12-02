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

        if (!canDelete(deletePostInputData.getPostId(),
                deletePostInputData.getAuthorId(),
                deletePostInputData.getCurrentUserId())) {
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
     * This method is private because it is part of the internal logic of the interactor.
     * @param postId       the ID of the post
     * @param authorId     the ID of the post's author
     * @param currentUserId the ID of the current user
     * @return true if the user can delete the post, false otherwise
     */
    private boolean canDelete(String postId, String authorId, String currentUserId) {
        User currentUser = userRepo.getCurrentUser();
        boolean isCreator = currentUserId.equals(authorId);

        String category = getCategoryForPost(postId);

        boolean isUserValid = currentUser != null;
        boolean isCategoryValid = category != null;

        boolean isModerator = isUserValid && isCategoryValid
                && currentUser.getModerating().contains(category);
        return isCreator || isModerator;
    }

    private String getCategoryForPost(String postId) {
        return postDataAccessObject.getPostCategory(postId);
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
