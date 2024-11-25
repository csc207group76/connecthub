package controller.createPost;

import controller.ViewModel;

public class createPostViewModel extends ViewModel<createPostState> {
    public createPostViewModel(String viewName) {
        super("create post");
        setState(new createPostState());
    }
}
