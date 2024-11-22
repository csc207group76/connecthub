package use_case.login;

import entity.User;
import org.json.JSONObject;

public interface LoginDataAccessInterface {
    /**
     * Returns the user with the given email.
     * @param email the email to look up
     * @return the user with the given email
     */
    JSONObject getUserByEmail(String email);

    /**
     * Set the currentUser.
     * @param user the current user.
     */
    void setCurrentUser(User user);

    /**
     * Check whether user exists by email.
     * @param email - email to query for.
     */
    boolean existsByEmail(String email);
}