package controller.post;

import controller.createComment.CreateCommentViewModel;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInputData;

/**
 * A controller for an individual post
 */
public class PostController {
    private final GetPostInputBoundary getPostInteractor; 

    public PostController(GetPostInputBoundary getPostInteractor) {
        this.getPostInteractor = getPostInteractor;
    }

    public void execute(String postId) {
        final GetPostInputData getPostInputData = new GetPostInputData(postId);
        try {
            this.getPostInteractor.getPost(getPostInputData);
        } catch (Exception ex) {

        }
    }

    /**
     * Switch to the CreateComment View
     */
    public void switchToCreateCommentView(PostState state){getPostInteractor.switchToCreateCommentView(state);}

    public void switchBack(CreateCommentViewModel createCommentViewModel) {
        getPostInteractor.switchBack(createCommentViewModel);
    }
}
