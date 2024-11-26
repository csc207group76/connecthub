package use_case.logout;
/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutDataAccessInterface logoutDB;
    private LogoutOutputBoundary logoutOutput;

    public LogoutInteractor(LogoutDataAccessInterface logoutDB, LogoutOutputBoundary logoutOutput) {
        this.logoutDB = logoutDB;
        this.logoutOutput = logoutOutput;
    }

    @Override
    public void logoutUser(LogoutInputData logoutInputData) {
        final String userID = logoutInputData.getUserID();
        logoutDB.logoutUser();
        LogoutOutputData logoutOutputData = new LogoutOutputData(userID, true);
        logoutOutput.prepareSuccessView(logoutOutputData);
    }
}
