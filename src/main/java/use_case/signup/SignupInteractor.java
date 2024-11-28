package use_case.signup;

import entity.User;
import entity.UserFactory;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupDataAccessInterface signupDB;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.signupDB = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void signupUser(SignupInputData signupInputData) {
        if (signupDB.existsByEmail(signupInputData.getEmail())) {
            userPresenter.prepareFailView("User already exists.");
            throw new UserExistsException("User already exists");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            final User user = userFactory.create(
                signupInputData.getUsername(),
                signupInputData.getPassword(),
                generateUserID(),
                signupInputData.getBirthDate(),
                signupInputData.getFullName(),
                signupInputData.getEmail(),
                new ArrayList<String>(),
                new ArrayList<String>()
            );
            signupDB.save(user);
            signupDB.setCurrentUser(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }

    private String generateUserID() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
}
