package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class ModeratorUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String email, String userID, String birthdate, String fullname) {
        return new ModeratorUser(name, password, email, userID, birthdate, fullname);
    }

}
