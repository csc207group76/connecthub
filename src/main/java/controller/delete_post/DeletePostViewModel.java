package controller.delete_post;

import controller.ViewModel;

/**
 * The View Model for the Delete Post View.
 */
public class DeletePostViewModel extends ViewModel<DeletePostState> {

    /**
     * Initializes the DeletePostViewModel with its state and view name.
     */
    public DeletePostViewModel() {
        super("delete post");
        setState(new DeletePostState());
    }
}
