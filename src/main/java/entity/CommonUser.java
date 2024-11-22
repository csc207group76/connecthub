package entity;

import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {
    private final String username;
    private final String password;
    private final int accessLevel;
    private final String userID;
    private final String birthDate;
    private final String fullName;
    private final String email;
    private final List<String> moderating;
    private final List<String> posts;

    public CommonUser(String name, String password, String userID, String birthDate, String fullName, 
                      String email, List<String> moderating, List<String> posts) {
        this.username = name;
        this.password = password;
        this.userID = userID;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.email = email;
        this.moderating = moderating;
        this.posts = posts;
        this.accessLevel = 0;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getAccessLevel() {
        return accessLevel;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public List<String> getModerating() {
        return moderating;
    }

    @Override
    public List<String> getPosts() {
        return posts;
    }
}
