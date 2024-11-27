package use_case.get_user;

public class GetUserInputData {
    private final String userID;

    public GetUserInputData(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
