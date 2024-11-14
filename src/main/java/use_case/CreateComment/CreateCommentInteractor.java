package use_case.CreateComment;

import entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Creation Comment Interactor
 */
public class CreateCommentInteractor implements CreateCommentInputBoundary {
    private final CreateCommentDataAccessInterface commentDataAccessObject;
    private final CreateCommentOutputBoundary commentPresenter;
    private final CommentFactory commentFactory;

    public CreateCommentInteractor(CreateCommentDataAccessInterface commentDataAccessObject,
                                   CreateCommentOutputBoundary commentPresenter,
                                   CommentFactory commentFactory) {
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
        this.commentFactory = commentFactory;
    }

    @Override
    public void execute(CreateCommentInputData createCommentInputData) {
        CommentContent content = new CommentContent(
                createCommentInputData.getContent(),
                createCommentInputData.getAttachmentPath(),
                createCommentInputData.getFileType()
        );

        Comment comment = commentFactory.createComment(
                createCommentInputData.getAuthor(),
                content.getBody(),
                content.getAttachmentPath(),
                content.getFileType()
        );

        // TODO :will have to fix later because you will need to access database
        commentDataAccessObject.save(comment);

        CreateCommentOutputData createCommentOutputData = new CreateCommentOutputData(
                comment.getEntryID(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getPostedDate(),
                comment.getLikes(),
                comment.getDislikes(),
                true
        );

        commentPresenter.prepareSuccessView(createCommentOutputData);
    }

    /**
     * Switches to the comment view.
     */
    public void switchToCommentView() {
        commentPresenter.switchToCreateCommentView();
    }
}
