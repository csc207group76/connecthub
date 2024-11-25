package controller.createPost;

import controller.ViewManagerModel;
import controller.signup.SignupState;
import use_case.createPost.CreatePostOutputBoundary;
import use_case.createPost.CreatePostOutputData;
;

public class createPostPresenter implements CreatePostOutputBoundary {

    private final createPostViewModel createPostViewModel;
    private final ViewManagerModel viewManagerModel;

    public createPostPresenter(controller.createPost.createPostViewModel createPostViewModel, ViewManagerModel viewManagerModel) {
        this.createPostViewModel = createPostViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreatePostOutputData response) {
        final createPostState createPostState = createPostViewModel.getState();
        createPostState.setContent(response.getContent());
        this.createPostViewModel.setState(createPostState);
        createPostViewModel.firePropertyChanged();

        viewManagerModel.setState(createPostViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final createPostState createPostState = createPostViewModel.getState();
        createPostState.setPostTitleError(errorMessage);
        createPostViewModel.firePropertyChanged();
    }
}
