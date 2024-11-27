package controller.delete_post;

import controller.ViewManagerModel;
import use_case.delete_post.DeletePostOutputBoundary;
import use_case.delete_post.DeletePostOutputData;

public class DeletePostPresenter implements DeletePostOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DeletePostViewModel deletePostViewModel;

    public DeletePostPresenter(ViewManagerModel viewManagerModel,
                               DeletePostViewModel deletePostViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.deletePostViewModel = deletePostViewModel;
    }

    /**
     * Prepares the success view for the Delete Post Use Case.
     * @param outputData the output data indicating successful deletion
     */
    @Override
    public void prepareSuccessView(DeletePostOutputData outputData) {
        final DeletePostState deletePostState = this.deletePostViewModel.getState();
        deletePostState.setPostID(outputData.getPostId());
        deletePostState.setSuccessMessage("Post with ID " + outputData.getPostId() + " was successfully deleted.");
        deletePostState.setDeleteError(null);

        this.deletePostViewModel.setState(deletePostState);
        this.deletePostViewModel.firePropertyChanged();

        this.viewManagerModel.setState(deletePostViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


    /**
     * Prepares the failure view for the Delete Post Use Case.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final DeletePostState deletePostState = this.deletePostViewModel.getState();
        deletePostState.setDeleteError(errorMessage);
        deletePostState.setSuccessMessage(null);

        this.deletePostViewModel.setState(deletePostState);
        this.deletePostViewModel.firePropertyChanged();
    }

    @Override
    public void switchToDeletePostView() {
    }

    public void switchToHomePageView() {}


}
