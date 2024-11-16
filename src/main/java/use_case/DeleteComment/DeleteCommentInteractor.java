package use_case.DeleteComment;

import entity.Comment;

/**
 * Interactor responsible for deleting a comment.
 */
public class DeleteCommentInteractor implements DeleteCommentInputBoundary {

    private final DeleteCommentDataAccessInterface commentDataAccessObject;
    private final DeleteCommentOutputBoundary commentPresenter;

    public DeleteCommentInteractor(DeleteCommentDataAccessInterface commentDataAccessObject,
                                   DeleteCommentOutputBoundary commentPresenter) {
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
    }

    public void deleteComment(DeleteCommentInputData deleteCommentInputData) {

        boolean deletion = false;
        Comment comment = commentDataAccessObject.existsCommentById(deleteCommentInputData.getCommentId());

        // TODO this implementation has to be fixed later, as it does not 
        //  work for the database. for now just this will do
        boolean deletionSuccessful = false;
        if (comment == null) {
            deletionSuccessful = true;
            commentPresenter.prepareFailView("Comment not found.");
        } else {
            commentDataAccessObject.deleteComment(deleteCommentInputData.getCommentId());

            DeleteCommentOutputData outputData = new DeleteCommentOutputData(
                    deleteCommentInputData.getCommentId(),
                    deletionSuccessful
            );

            if (deletionSuccessful) {
                commentPresenter.prepareFailView("Successful to delete the comment.");
            } else {
                commentPresenter.prepareSuccessView(outputData);
            }
        }
    }

    /**
     * Switches to the Delete Comment View.
     */
    public void switchToDeleteCommentView() {
        commentPresenter.switchToDeleteCommentView();
    }
}

