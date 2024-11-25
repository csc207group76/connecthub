package controller.post;

import controller.ViewModel;

public class PostViewModel extends ViewModel<PostState> {
    public static final String COMMENTS_LABEL = "Comments";

    public PostViewModel() {
        super("post");
        setState(new PostState());
    }
}
