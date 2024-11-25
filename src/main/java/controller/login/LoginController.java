package controller.login;

import use_case.login.AccountDoesNotExistException;
import use_case.login.IncorrectPasswordException;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    /**
     * Executes the Login Use Case.
     * @param email the email of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String email, String password) {
        final LoginInputData loginInputData = new LoginInputData(email, password);
        try {
            loginInputBoundary.LoginUser(loginInputData);
        } catch (AccountDoesNotExistException e){
            //System.out.print("Error: Account doesn't exist");
        } catch (IncorrectPasswordException e) {
            //System.out.println("Error: Incorrect password");
        }
    }
}