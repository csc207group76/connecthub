package entity;

/**
 * A simple implementation of the User interface.
 */
public class AnonymousUser implements User {

    private final String name;
    private final String password;
    private final int accessLevel;


    public AnonymousUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.accessLevel = 0;
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
}
