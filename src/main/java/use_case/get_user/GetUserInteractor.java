package use_case.get_user;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;

public class GetUserInteractor implements GetUserInputBoundary {
    private final GetUserDataAccessInterface userRepo;
    private final UserFactory userFactory;

    public GetUserInteractor(GetUserDataAccessInterface userRepo, UserFactory userFactory) {
        this.userRepo = userRepo;
        this.userFactory = userFactory;
    }

    @Override
    public User getUserByID(GetUserInputData getUserInputData) {
        if (!this.userRepo.existsByID(getUserInputData.getUserID())) {
            throw new UserDoesNotExistException(
                "User with ID " + getUserInputData.getUserID() + " does not exist");
        } else {
            JSONObject user = this.userRepo.getUserById(getUserInputData.getUserID());
            return jsonObjectToUser(user);
        }
    }

    @Override
    public User getCurrentUser() {
        return this.userRepo.getCurrentUser();
    }

    private User jsonObjectToUser(JSONObject user) {
        JSONArray moderatingData = user.getJSONArray("moderating");
        List<String> moderating = new ArrayList<>();
        for (int i = 0; i < moderatingData.length(); i++){
            moderating.add(moderatingData.getString(i));
        }

        JSONArray postsData = user.getJSONArray("posts");
        List<String> posts = new ArrayList<>();
        for (int i = 0; i < postsData.length(); i++){
            posts.add(postsData.getString(i));
        }

        User currentUser = this.userFactory.create(
            user.getString("username"),
            user.getString("password"),
            user.getString("userId"),
            user.getString("birth_date"),
            user.getString("full_name"),
            user.getString("email"),
            moderating,
            posts
        );

        return currentUser;
    }
}
