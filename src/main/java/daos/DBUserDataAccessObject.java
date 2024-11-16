package daos;

import entity.User;
import use_case.signup.SignupDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class DBUserDataAccessObject implements SignupDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUser;

    @Override
    public boolean existsByUsername(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean existsByID(String userID) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

}
