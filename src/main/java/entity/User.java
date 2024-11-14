package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the access level of the user.
     * @return the access level of the user.
     */
    int getAccessLevel();

    String getID();

    String getEmail();

    String getBirthDate();

    String getFullName();
}
