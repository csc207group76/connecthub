package use_case.login;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String msg) {
        super(msg);
    }
}
