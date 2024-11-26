package controller.createPost;

import controller.ViewModel;

public class createPostViewModel extends ViewModel<createPostState> {

    public static final String CONTENT_LABEL = "Content";

    public createPostViewModel(String viewName) {

        super("create post");
        setState(new createPostState());
    }
}
