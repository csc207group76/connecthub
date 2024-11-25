package controller.createPost;

import entity.Comment;
import entity.PostContent;
import entity.User;
import use_case.createPost.CreatePostInputBoundary;
import use_case.createPost.CreatePostInputData;


import java.time.LocalDateTime;
import java.util.List;

public class createPostController {

    private final CreatePostInputBoundary createPostInputBoundary;

    public createPostController(CreatePostInputBoundary createPostInteractor) {
        this.createPostInputBoundary = createPostInteractor;
    }

    public void execute(String author, PostContent content, String attachmentPath,
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
