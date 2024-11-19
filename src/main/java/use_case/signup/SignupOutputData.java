package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String username;
    private final String userID;
    private final boolean SignupSuccessful;

    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.userID = "123"; // generate randomly

        this.SignupSuccessful = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isSignupSuccessful() {
        return SignupSuccessful;
    }

    public String getUserID() {
        return userID;
    }
}
