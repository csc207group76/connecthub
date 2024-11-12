package use_case.CreateComment;

import entity.NormalComment;

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
        // Validate the input data (e.g., ensure content and author are not empty)
        if (createCommentInputData.getContent() == null || createCommentInputData.getContent().isEmpty()) {
            commentPresenter.prepareFailView("Content cannot be empty.");
            return;
        }

        if (createCommentInputData.getAuthor() == null || createCommentInputData.getAuthor().isEmpty()) {
            commentPresenter.prepareFailView("Author cannot be empty.");
            return;
        }

        NormalComment comment = new NormalComment(createCommentInputData.getContent(), createCommentInputData.getAuthor());

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
        commentPresenter.switchToCommentView();
    }
}
