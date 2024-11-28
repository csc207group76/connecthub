package use_case.get_user;

import org.json.JSONObject;

import entity.User;

public interface GetUserDataAccessInterface {
    public boolean existsByID(String userID);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public JSONObject getUserById(String userID);

    public JSONObject getUserByUsername(String username);

    public JSONObject getUserByEmail(String email);

    public User getCurrentUser();
}
