package controller.getpost;

import use_case.getpost.GetPostOutputBoundary;
import use_case.getpost.GetPostOutputData;

/**
 * The Presenter for the Get Post Use Case.
 */
public class GetPostPresenter implements GetPostOutputBoundary {
    private final GetPostViewModel getPostViewModel;

    public GetPostPresenter(GetPostViewModel viewModel) {
        this.getPostViewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(GetPostOutputData outputData) {
        getPostViewModel.setEntryID(outputData.getEntryID());
        getPostViewModel.setPostContent(outputData.getPostContent());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        getPostViewModel.setErrorMessage(errorMessage);
    }
}
