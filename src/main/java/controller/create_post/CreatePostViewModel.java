package controller.create_post;

import controller.ViewModel;

public class CreatePostViewModel extends ViewModel<CreatePostState> {
    public CreatePostViewModel() {
        super("create post");
        setState(new CreatePostState());
    }
}
