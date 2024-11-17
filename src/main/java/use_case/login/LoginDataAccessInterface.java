package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {
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
    void saveUser(User user);

    /**
     * Returns the user with the given email.
     * @param email the email to look up
     * @return the user with the given email
     */
    User getUserByEmail(String email);

    /**
     * Set the currentUser.
     * @param user the current user.
     */
    void setCurrentUser(User user);

    /**
     * get the currentUser Email
     * @return the current User email
     */
    String getCurrentUserEmail();
}
