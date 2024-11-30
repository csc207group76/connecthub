package controller.createComment;

import controller.ViewManagerModel;
import use_case.createComment.CreateCommentOutputBoundary;
import use_case.createComment.CreateCommentOutputData;

public class CreateCommentPresenter implements CreateCommentOutputBoundary {
    private final CreateCommentViewModel createCommentViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateCommentPresenter(CreateCommentViewModel createCommentViewModel, ViewManagerModel viewManagerModel) {
        this.createCommentViewModel = createCommentViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateCommentOutputData response) {
        // If success it is going back to the post view by postController.switchBack
        // Might use it for test purpose
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // If fail then do nothing just stay on the createComment page
        // Might use it for test purpose
    }

}
