package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class AnonymousUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String email, String userID,
                       String birthdate, String fullname) {
        return new AnonymousUser(name, password, email, userID, birthdate, fullname);
    }
}
