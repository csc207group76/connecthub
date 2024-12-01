package controller.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

public class LogoutController {
    private final LogoutInputBoundary logoutInputBoundary;

    public LogoutController(LogoutInputBoundary logoutInputBoundary) {
        this.logoutInputBoundary = logoutInputBoundary;
    }

    /**
     * Executes the Logout Use Case.
     * @param userID the userID of the user logging out
     */
    public void execute(String userID) {
        final LogoutInputData logoutInputData = new LogoutInputData(userID);
        logoutInputBoundary.logoutUser(logoutInputData);
    }
}
