package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {
    /**
     * Check if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existByUsername(String username);

    /**
     * Check if the given userID exists.
     * @param userID the userID to look for
     * @return true if a user with the given userID exists; false otherwise
     */
    boolean existByID(String userID);

    /**
     * Check if the given email exists.
     * @param email the email to look for
     * @return true if a user with the given email exists; false otherwise
     */
    boolean existByEmail(String email);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     * @param email the email to look up
     * @return the user with the given email
     */
    User getUserByEmail(String email);

    /**
     * Returns the user with the given userID.
     * @param userID the userID to look up
     * @return the user with the given userID
     */
    User getUserByID(String userID);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User getUserByUsername(String username);

    /**
     * Set the currentUser.
     * @param user the current user.
     */
    void setCurrentUser(User user);

    /**
     * Get Current User ID.
     * @return the userID
     */
    String getCurrentUserID();

    /**
     * Get Current User Email.
     * @return the userEmail
     */
    String getCurrentUserEmail();
}
