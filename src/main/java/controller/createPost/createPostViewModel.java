package controller.createPost;

import controller.ViewModel;
import controller.create_post.CreatePostState;

public class createPostViewModel extends ViewModel<CreatePostState> {

    public static final String CONTENT_LABEL = "Content";

    public createPostViewModel(String viewName) {

        super("create post");
        setState(new CreatePostState());
    }
}
