package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import controller.ViewManagerModel;
import controller.login.LoginViewModel;
//import controller.login.LoginViewModel;
import controller.signup.SignupController;
import controller.signup.SignupPresenter;
import controller.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupDataAccessInterface;
import view.SignupView;

/**
 * This class contains the static factory function for creating the SignupView.
 */
public final class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {

    }

    /**
     * Factory function for creating the SignupView.
     * @param viewManagerModel the ViewManagerModel to inject into the SignupView
     * @param signupViewModel the SignupViewModel to inject into the SignupView
     * @param userDataAccessObject the SignupUserDataAccessInterface to inject into the SignupView
     * @return the LoginView created for the provided input classes
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel,
            SignupViewModel signupViewModel, 
            LoginViewModel loginViewModel,
            SignupDataAccessInterface userDataAccessObject) {

        final SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
        return new SignupView(signupController, signupViewModel);

    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            SignupViewModel signupViewModel,
                                                            LoginViewModel loginViewModel,
                                                            SignupDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        final UserFactory userFactory = new CommonUserFactory();

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
