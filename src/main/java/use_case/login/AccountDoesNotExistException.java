package use_case.login;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String msg) {
        super(msg);
    }
}
