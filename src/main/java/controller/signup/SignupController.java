package controller.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary SignupInputBoundary;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.SignupInputBoundary = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String userID, String password1, String password2, String email,
                        String birthDate, String fullName) {
        final SignupInputData signupInputData = new SignupInputData(
                username, userID, password1, password2, email, birthDate, fullName);

        SignupInputBoundary.SignupUser(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        SignupInputBoundary.switchToLoginView();
    }
}