package controller.createComment;

import controller.ViewModel;
import controller.post.PostState;

public class CreateCommentViewModel extends ViewModel<CreateCommentState> {
    private PostState postState;

    public CreateCommentViewModel() {
        super("create comment");
        setState(new CreateCommentState());
    }

    public void setPostState(PostState postState) {
        this.postState = postState;
    }

    public PostState getPostState() {
        return postState;
    }
}
