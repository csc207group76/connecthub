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
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!loginDB.existByUsername(username)) {
            loginOutput.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = loginDB.getUserByUsername(username).getPassword();
            if (!password.equals(pwd)) {
                loginOutput.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {
                final User user = loginDB.getUserByUsername(loginInputData.getUsername());
                loginDB.setCurrentUser(user);
                final LoginOutputData loginOutputData = new LoginOutputData(user.getName(),
                        user.getID(), user.getEmail(), user.getBirthDate(), user.getFullName(),
                        user.getPassword(), true);
                loginOutput.prepareSuccessView(loginOutputData);
            }
        }
    }
}