package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements use_case.login.LoginInputBoundary {
    private final use_case.login.LoginDataAccessInterface loginDB;
    private final LoginOutputBoundary loginOutput;

    public LoginInteractor(use_case.login.LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.loginDB = userDataAccessInterface;
        this.loginOutput = loginOutputBoundary;
    }

    @Override
    public void LoginUser(use_case.login.LoginInputData loginInputData) {
        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();
        if (!loginDB.existByEmail(email)) {
            loginOutput.prepareFailView(email + ": Account does not exist.");
        }
        else {
            final String pwd = loginDB.getUserByEmail(email).getPassword();
            if (!password.equals(pwd)) {
                loginOutput.prepareFailView("Incorrect password for \"" + email + "\".");
            }
            else {
                final User user = loginDB.getUserByEmail(loginInputData.getEmail());
                loginDB.setCurrentUser(user);
                final LoginOutputData loginOutputData = new LoginOutputData(
                        user.getName(), user.getPassword(), true);
                loginOutput.prepareSuccessView(loginOutputData);
            }
        }
    }
}