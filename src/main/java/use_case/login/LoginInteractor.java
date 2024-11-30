package use_case.login;

import entity.Comment;
import entity.UserFactory;
import entity.User;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface loginDB;
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
    public void LoginUser(LoginInputData loginInputData) {

        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();

        // First check if the email exists
        if (!loginDB.existsByEmail(email)) {
            loginOutput.prepareFailView(email + ": Account does not exist.");
            throw new AccountDoesNotExistException(email + ": Account does not exist.");
        }
        // The mailbox exists, check if the password is correct
        else {
            final JSONObject userData = loginDB.getUserByEmail(loginInputData.getEmail());
            final User user = this.jsonObjectToUser(userData);
            final String pwd = user.getPassword();
            // The password is incorrect, throw exception
            if (!password.equals(pwd)) {
                loginOutput.prepareFailView("Incorrect password for \"" + email + "\".");
                throw new IncorrectPasswordException("Incorrect password for \"" + email + "\".");
            }
            // The password is correct, login the User
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

        JSONArray commentData = user.getJSONArray("comments");
        List<String> comments = new ArrayList<>();
        for (int i = 0; i < postsData.length(); i++){
            comments.add(commentData.getString(i));
        }

        return this.userFactory.create(
            user.getString("username"), 
            user.getString("password"),
            user.getString("userId"),
            user.getString("birth_date"),
            user.getString("full_name"),
            user.getString("email"),
            moderating,
            posts,
            comments
        );
    }

    public void switchToSignupView(){loginOutput.switchToSignupView();}

    public void switchToHomePage(){loginOutput.switchToHomePage();}
}