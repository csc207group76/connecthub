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

        final boolean userCanDelete = canDelete(deletePostInputData);
        if (!userCanDelete) {
            postPresenter.prepareFailView("User does not have permission to delete this post.");
            throw new DeletePostFailedException("User does not have permission to delete this post.");
        }

        try {
            postDataAccessObject.deletePost(deletePostInputData.getPostId());

            userRepo.removePostFromUser(deletePostInputData.getUserId(), deletePostInputData.getPostId());

            final DeletePostOutputData outputData = new DeletePostOutputData(
                    deletePostInputData.getPostId(),
                    true

            );
            postPresenter.prepareSuccessView(outputData);
        }
        catch (Exception e) {
            postPresenter.prepareFailView("Failed to delete the post.");
            throw new DeletePostFailedException("Failed to delete the post.");
        }
    }

    /**
     * Check if the user can delete the post.
     * @param post the post to be deleted
     * @return true if the user can delete the post, false otherwise
     */
    public boolean canDelete(DeletePostInputData post) {
        final User currentUser = userRepo.getCurrentUser();
        return currentUser.getUserID().equals(post.getUserId())
                || currentUser.getModerating().contains(post.getPostId());
    }

    // public static void main(String[] args) {
    //    final String postId = "97428153-a736-4b85-b50a-fd11b8e1420f";
    //    final String userId = "306cd795-b9aa-4669-969b-62d82e580ab0";
    //   final DeletePostInputData deletePostInputData = new DeletePostInputData(postId, userId);}

}
