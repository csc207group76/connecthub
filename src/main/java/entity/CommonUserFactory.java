package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String email, String userID,
                       String birthdate, String fullname) {
        return new CommonUser(name, password, email, userID, birthdate, fullname);
    }
}
