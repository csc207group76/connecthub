package app;

import controller.ViewManagerModel;
import controller.logged_in.LoggedInViewModel;
import controller.login.LoginController;
import controller.login.LoginPresenter;
import controller.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import view.LoginView;

/**
 * This class contains the static factory function for creating the LoginView.
 */
public final class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoginView.
     * @param viewManagerModel the ViewManagerModel to inject into the LoginView
     * @param loginViewModel the LoginViewModel to inject into the LoginView
     * @param loggedInViewModel the LoggedInViewModel to inject into the LoginView
     * @param userDataAccessObject the LoginUserDataAccessInterface to inject into the LoginView
     * @return the LoginView created for the provided input classes
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginDataAccessInterface userDataAccessObject) {

        final LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel,
                loggedInViewModel, userDataAccessObject);
        return new LoginView(loginViewModel, loginController);

    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}