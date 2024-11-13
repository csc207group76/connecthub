package use_case.CreateComment;

import entity.ModeratorUser;
import entity.NormalComment;
import entity.User;

/**
 * The Creation Comment Interactor.
 */
public class CreateCommentInteractor implements CreateCommentInputBoundary {
    private final CreateCommentDataAccessInterface commentDataAccessObject;
    private final CreateCommentOutputBoundary commentPresenter;

    public CreateCommentInteractor(CreateCommentDataAccessInterface commentDataAccessObject,
                                   CreateCommentOutputBoundary commentPresenter) {
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
    }

    @Override
    public void execute(CreateCommentInputData createCommentInputData) {
        if (createCommentInputData.getContent() == null || createCommentInputData.getContent().isEmpty()) {
            commentPresenter.prepareFailView("Content cannot be empty.");
            return;
        }

        if (createCommentInputData.getAuthor() == null) {
            commentPresenter.prepareFailView("Author cannot be empty.");
            return;
        }

        if (createCommentInputData.getCommentId() == null || createCommentInputData.getCommentId().isEmpty()) {
            commentPresenter.prepareFailView("Comment ID cannot be empty.");
            return;
        }

        if (createCommentInputData.getEntryId() == null || createCommentInputData.getEntryId().isEmpty()) {
            commentPresenter.prepareFailView("Entry ID cannot be empty.");
            return;
        }

        if (createCommentInputData.getModerator() == null) {
            commentPresenter.prepareFailView("Moderator cannot be empty.");
            return;
        }

        NormalComment comment = new NormalComment(
                createCommentInputData.getCommentId(),
                createCommentInputData.getEntryId(),
                createCommentInputData.getContent(),
                createCommentInputData.getAuthor(),
                createCommentInputData.getModerator()
        );

        commentDataAccessObject.save(comment);


        CreateCommentOutputData createCommentOutputData = new CreateCommentOutputData(
                comment.getCommentId(),
                comment.getEntryId(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getTimestamp(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getModerator(),
                false
        );
    }

    /**
     * Switches to the comment view.
     */
    public void switchToCommentView() {
        commentPresenter.switchToCreateCommentView();
    }
}
