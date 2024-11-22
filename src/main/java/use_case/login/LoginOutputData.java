package use_case.login;

public class LoginOutputData {
    private String email;
    private boolean loginSuccessful;
    private String password;

    public LoginOutputData(String email, String password, boolean loginSuccessful) {
        this.email = email;
        this.loginSuccessful = loginSuccessful;
        this.password = password;
    }

    /**
     * Get the current Email
     * @return the current Email
     */
    public String getUserEmail() {
        return email;
    }

    /**
     * Get the current Password
     * @return the current Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the current UserEmail
     */
    public void setUserEmail(String email) {
        this.email = email;
    }

    /**
     * Set the current password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}