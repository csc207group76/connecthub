package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class AnonymousUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new AnonymousUser(name, password);
    }
}
