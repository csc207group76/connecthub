package entity;

import java.util.List;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     *
     * @param name       the name of the new user
     * @param password   the password of the new user
     * @param moderating
     * @param posts
     * @return the new user
     */
    User create(String name, String password, String userID, String birthDate, String fullName, 
                String email, List<String> moderating, List<String> posts);

}
