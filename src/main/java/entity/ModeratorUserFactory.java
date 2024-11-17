package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class ModeratorUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new ModeratorUser(name, password);
    }
}
