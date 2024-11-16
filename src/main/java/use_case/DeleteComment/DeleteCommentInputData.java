package use_case.DeleteComment;

/**
Input Data for the Delete Comment use-case .
 */
public class DeleteCommentInputData {
    // TODO will have to be changed when database is setup
    private String commentId;

    public DeleteCommentInputData(String commentId) {
    this.commentId = commentId;
    }
    String getCommentId() {
        return commentId;
    }

}
