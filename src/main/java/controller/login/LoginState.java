package controller.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String username = "";
    private String loginError;
    private String password = "";
    private String userID = "";
    private String email = "";
    private String birthdate = "";
    private String fullname = "";

    public String getUsername() {
        return username;
    }

    public String getUserID(){ return userID; }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public String getBirthDate() { return birthdate; }

    public String getFullName() {return fullname; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
