package use_case.logout;
/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutDataAccessInterface logoutDB;

    public LogoutInteractor(LogoutDataAccessInterface logoutDB) {
        this.logoutDB = logoutDB;
    }

    @Override
    public void logoutUser(LogoutInputData logoutInputData) {
        final String userID = logoutInputData.getUserID();
        logoutDB.logoutUser();
        LogoutOutputData logoutOutputData = new LogoutOutputData(userID, true);

        // This is not needed unless we want to prepare a log out view
        // logoutOutput.prepareSuccessView(logoutOutputData);
    }
}
