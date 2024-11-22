package use_case.signup;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if the given userID exists.
     * @param userID the username to look for
     * @return true if a user with the given userID exists; false otherwise
     */
    boolean existsByID(String userID);

    /**
     * Checks if the given email exists.
     * @param email the email to look for
     * @return true if a user with the given email exists; false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);
}
