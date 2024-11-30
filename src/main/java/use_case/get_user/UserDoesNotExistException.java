package use_case.get_user;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
