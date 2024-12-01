package controller.logout;

import controller.ViewManagerModel;
import controller.login.LoginState;
import controller.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;
import view.LoginView;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerModel viewManager;
    private final LoginViewModel viewModel;

    public LogoutPresenter(ViewManagerModel viewManager,
                           LoginViewModel viewModel) {
        this.viewManager = viewManager;
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {
        viewManager.setState(viewModel.getViewName());
        viewManager.firePropertyChanged();
    }
}