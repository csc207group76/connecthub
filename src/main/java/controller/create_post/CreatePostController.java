package controller.create_post;

import entity.Comment;
import entity.User;
import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInputData;

import java.util.List;

public class CreatePostController {

    private final CreatePostInputBoundary createPostInputBoundary;

    public CreatePostController(CreatePostInputBoundary createPostInteractor) {
        this.createPostInputBoundary = createPostInteractor;
    }

    public void execute(String author, String content, String attachmentPath,
                        String fileType, int dislikes, int likes, String postTitle,
                        List<User> moderators, List<Comment> comments, String category) {
        final CreatePostInputData createInputData = new CreatePostInputData(author, content,
                attachmentPath, fileType, dislikes, likes, postTitle, moderators, comments, category);

        createPostInputBoundary.createPost(createInputData);
    }

    // TODO: will do after merge
    public void switchToHomePageview() {

    }
}
