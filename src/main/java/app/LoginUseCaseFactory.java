package app;

import controller.ViewManagerModel;
import controller.homepage.HomepageViewModel;
import controller.login.LoginController;
import controller.login.LoginPresenter;
import controller.login.LoginViewModel;
import controller.signup.SignupViewModel;
import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import view.LoginView;

/**
 * This class contains the static factory function for creating the LoginView.
 */
public final class LoginUseCaseFactory {

    private LoginUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoginView.
     * @param viewManagerModel the ViewManagerModel to inject into the LoginView
     * @param loginViewModel the LoginViewModel to inject into the LoginView
     * @param homepageViewModel the homepageViewModel to inject into the LoginView
     * @param userDataAccessObject the LoginDataAccessInterface to inject into the LoginView
     * @param signupViewModel the SignupViewModel to inject into the LoginView
     * @return the LoginView created for the provided input classes
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomepageViewModel homepageViewModel,
            LoginDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel) {

        final LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel,
                homepageViewModel, userDataAccessObject, signupViewModel);
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomepageViewModel homepageViewModel,
            LoginDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel) {

        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homepageViewModel,
                loginViewModel, signupViewModel);

        final UserFactory userFactory = new CommonUserFactory();
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(loginInteractor);
    }
}
