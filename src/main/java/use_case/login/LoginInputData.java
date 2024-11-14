package use_case.login;

import use_case.logout.LogoutInputBoundary;

public class LoginInputData {

    private String userID;
    private String userName;
    private String password;
    private String email;
    private String birthData;
    private String fullName;

    public LoginInputData(String userID, String userName, String password,
                          String email, String birthData, String fullName) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthData = birthData;
        this.fullName = fullName;
    }

    public String getUsername() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthData() {
        return birthData;
    }
}
