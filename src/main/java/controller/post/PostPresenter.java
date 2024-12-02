package controller.post;

import controller.ViewManagerModel;
import use_case.delete_post.DeletePostOutputBoundary;
import use_case.delete_post.DeletePostOutputData;
import use_case.getpost.GetPostOutputBoundary;
import use_case.getpost.GetPostOutputData;

public class PostPresenter implements GetPostOutputBoundary, DeletePostOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PostViewModel postViewModel;

    // TODO will need a home page view model
    public PostPresenter(ViewManagerModel viewManagerModel,
                         PostViewModel postViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.postViewModel = postViewModel;
    }
    
    /**
     * Prepares the success view for the Get Post Use Case.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(GetPostOutputData outputData) {
        final PostState postState = this.postViewModel.getState();
        postState.setPostID(outputData.getEntryID());
        postState.setPostTitle(outputData.getPostTitle());
        postState.setPostContent(outputData.getPostContent().getBody());
        postState.setComments(outputData.getComments());

        postState.setPostContentError(null);
        postState.setCommentsError(null);
        postState.setPostIDError(null);
        postState.setPostTitleError(null);
        postState.setAuthorIDError(null);

        this.postViewModel.setState(postState);
        this.postViewModel.firePropertyChanged();

        this.viewManagerModel.setState(postViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(DeletePostOutputData outputData) {
        this.viewManagerModel.setState("homepage");
        this.viewManagerModel.firePropertyChanged();
    }


    /**
     * Prepares the failure view for the Get Post Use Case.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final PostState postState = this.postViewModel.getState();
        postState.setPostContentError(errorMessage);
        postState.setPostIDError(errorMessage);
        postState.setPostTitleError(errorMessage);
        this.postViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPostView() {}

    public void switchToHomePageView() {
        viewManagerModel.setState(postViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
