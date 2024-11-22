package use_case.login;

public class LoginInputData {

    private String email;
    private String password;

    public LoginInputData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Get the current Email
     * @return the current Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the current password
     * @return the current password
     */
    public String getPassword() {
        return password;
    }
}