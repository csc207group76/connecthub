package use_case.login;

import entity.UserFactory;
import entity.User;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements use_case.login.LoginInputBoundary {
    private final use_case.login.LoginDataAccessInterface loginDB;
    private final LoginOutputBoundary loginOutput;
    private final UserFactory userFactory;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           UserFactory userFactory) {
        this.loginDB = userDataAccessInterface;
        this.loginOutput = loginOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void LoginUser(use_case.login.LoginInputData loginInputData) {
        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();
        if (!loginDB.existsByEmail(email)) {
            loginOutput.prepareFailView(email + ": Account does not exist.");
            throw new AccountDoesNotExistException(email + ": Account does not exist.");
        }
        else {
            final JSONObject userData = loginDB.getUserByEmail(loginInputData.getEmail());
            final User user = this.jsonObjectToUser(userData);
            final String pwd = user.getPassword();
            if (!password.equals(pwd)) {
                loginOutput.prepareFailView("Incorrect password for \"" + email + "\".");
                throw new IncorrectPasswordException(email + ": Account does not exist.");
            }
            else {
                loginDB.setCurrentUser(user);
                final LoginOutputData loginOutputData = new LoginOutputData(
                        user.getEmail(), user.getPassword(), true);
                loginOutput.prepareSuccessView(loginOutputData);
            }
        }
    }

    private User jsonObjectToUser(JSONObject user) {
        JSONArray moderatingData = user.getJSONArray("moderating");
        List<String> moderating = new ArrayList<>();
        for (int i = 0; i < moderatingData.length(); i++){ 
            moderating.add(moderatingData.getString(i));
        } 

        JSONArray postsData = user.getJSONArray("posts");
        List<String> posts = new ArrayList<>();
        for (int i = 0; i < postsData.length(); i++){ 
            posts.add(postsData.getString(i));
        } 

        User currentUser = this.userFactory.create(
            user.getString("username"), 
            user.getString("password"),
            user.getString("userId"),
            user.getString("birth_date"),
            user.getString("full_name"),
            user.getString("email"),
            moderating,
            posts
        );

        return currentUser;
    }

    public void switchToSignupView(){loginOutput.switchToSignupView();}

    public void switchToHomePage(){loginOutput.switchToHomePage();}
}