package controller.post;

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
}
