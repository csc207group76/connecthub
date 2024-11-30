package use_case.logout;

public interface LogoutInputBoundary {

    /**
     * Logout the current User
     * @param logoutInputData the input data
     */
    void logoutUser(LogoutInputData logoutInputData);

    void switchToSignupView();
}
