package controller.logout;

import controller.ViewManagerModel;
import controller.signup.SignupViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerModel viewManager;
    private final SignupViewModel ViewModel;

    public LogoutPresenter(ViewManagerModel viewManager,
                           SignupViewModel viewModel) {
        this.viewManager = viewManager;
        this.ViewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {
        this.switchToSignupView();

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToSignupView() {
        viewManager.setState(ViewModel.getViewName());
        ViewModel.firePropertyChanged();
    }
}
