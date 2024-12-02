package use_case.logout;
/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {

    private LogoutDataAccessInterface logoutDB;
    private LogoutOutputBoundary logoutOutput;

    public LogoutInteractor(LogoutDataAccessInterface logoutDB,
                            LogoutOutputBoundary logoutOutputBoundary) {
        this.logoutDB = logoutDB;
        this.logoutOutput = logoutOutputBoundary;
    }

    @Override
    public void logoutUser(LogoutInputData logoutInputData) {
        final String userID = logoutInputData.getUserID();
        logoutDB.logoutUser();
        LogoutOutputData logoutOutputData = new LogoutOutputData(userID, true);

        // keeping it for testing purposes
        logoutOutput.prepareSuccessView(logoutOutputData);
    }
}
