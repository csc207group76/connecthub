package controller.createComment;

import entity.Comment;
import entity.Content;
import entity.User;
import use_case.createComment.CreateCommentInputBoundary;
import use_case.createComment.CreateCommentInputData;
import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInputData;

import java.util.List;

public class CreateCommentController {
    private final CreateCommentInputBoundary createCommentInputBoundary;

    public CreateCommentController(CreateCommentInputBoundary createCommentInteractor) {
        this.createCommentInputBoundary = createCommentInteractor;
    }

    public void execute(String entryID, User author, String content,
                        String commentDate, String attachmentPath, String fileType) {
        final CreateCommentInputData createInputData = new CreateCommentInputData(entryID, author,
                content, commentDate, attachmentPath, fileType);

        createCommentInputBoundary.createComment(createInputData);
    }

    public void switchToPostView() {

    }
}
