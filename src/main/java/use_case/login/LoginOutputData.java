package use_case.login;

public class LoginOutputData {
    private String userName;
    private boolean loginSuccessful;
    private String password;
    private String fullname;
    private String email;
    private String birthdate;
    private String userID;

    public LoginOutputData(String username,String userID, String email, String birthdate,
                           String fullname, String password, boolean loginSuccessful) {
        this.userName = username;
        this.loginSuccessful = loginSuccessful;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.birthdate = birthdate;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getfullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setfullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setbirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }
}
