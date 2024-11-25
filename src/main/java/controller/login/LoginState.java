package controller.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String email = "";
    private String emailError;
    private String passwordError;
    private String password = "";

    public String getEmail() {
        return email;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getPasswordError() {return passwordError;}

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public void setPasswordError(String passwordError) {this.passwordError = passwordError;}

    public void setPassword(String password) {
        this.password = password;
    }
}
