package use_case.createComment;

import daos.DBCommentDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.Comment;
import entity.CommentFactory;
import entity.User;

import java.util.List;

public class CreateCommentInteractor implements CreateCommentInputBoundary {

    private final CreateCommentDataAccessInterface dataAccess;
    private final DBUserDataAccessObject userRepo;
    private final CreateCommentOutputBoundary userPresenter;
    private final CommentFactory commentFactory;

    public CreateCommentInteractor(CreateCommentDataAccessInterface createCommentDataAccessInterface,
                                   CreateCommentOutputBoundary outputBoundary,
                                   DBUserDataAccessObject dataAccessObject,
                                   CommentFactory factory){
        this.commentFactory = factory;
        this.userRepo =  dataAccessObject;
        this.dataAccess = createCommentDataAccessInterface;
        this.userPresenter = outputBoundary;
    }

    @Override
    public void createComment(CreateCommentInputData inputData) {
        User user = this.userRepo.getCurrentUser();
        if (user == null) {
            userPresenter.prepareFailView("Please Sign In First!");
            throw new CreateCommentFailedException("Please sign in first!");
        }else if (inputData.getContent() == null){
            userPresenter.prepareFailView("Please fill in comment contents!");
            throw new CreateCommentFailedException("Please fill in comment contents!");
        }else {
            final Comment comment = this.commentFactory.createComment(
                    inputData.getEntryID(),
                    inputData.getAuthor(),
                    inputData.getContent(),
                    inputData.getAttachmentPath(),
                    inputData.getFileType());

            dataAccess.createComment(comment);
            List<String> userComments = user.getComments();
            userComments.add(comment.getEntryID());
            this.userRepo.updateUserComments(user);

            final CreateCommentOutputData outputData = new CreateCommentOutputData(
                    comment.getEntryID(),
                    comment.getAuthor(),
                    inputData.getContent(),
                    inputData.getCommentDate(),
                    true
            );
            userPresenter.prepareSuccessView(outputData);
        }
    }
}
