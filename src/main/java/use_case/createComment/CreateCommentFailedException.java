package use_case.createComment;

public class CreateCommentFailedException extends RuntimeException {

    public CreateCommentFailedException(String err) {
        super(err);
    }

}
