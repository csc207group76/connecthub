package entity;

/**
 * A simple implementation of the User interface.
 */
public class AnonymousUser implements User {

    private final String name;
    private final String password;
    private final int accessLevel;
    private final String email;
    private final String userID;
    private final String birthdate;
    private final String fullname;


    public AnonymousUser(String name, String password, String email, String userID,
                         String birthdate, String fullname) {
        this.name = name;
        this.password = password;
        this.accessLevel = 0;
        this.email = email;
        this.userID = userID;
        this.birthdate = birthdate;
        this.fullname = fullname;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getAccessLevel() {return accessLevel; }

    @Override
    public String getID() {
        return userID;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getBirthDate() {
        return birthdate;
    }

    @Override
    public String getFullName() {
        return fullname;
    }
}
