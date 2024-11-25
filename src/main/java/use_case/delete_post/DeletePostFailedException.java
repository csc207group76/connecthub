package use_case.delete_post;

/**
 * Exception for Delete post failures
 */
public class DeletePostFailedException extends RuntimeException {
    public DeletePostFailedException(String message) {
        super(message);
    }
}
