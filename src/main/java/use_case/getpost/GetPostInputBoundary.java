package use_case.getpost;

import java.util.List;

import controller.createComment.CreateCommentViewModel;
import controller.post.PostState;
import entity.Post;

import javax.swing.plaf.nimbus.State;

/**
 * The input boundary for the Get Post Use Case.
 */
public interface GetPostInputBoundary {
    /**
     * Executes the get post use case.
     * @param getPostInputData the get post input data
     * @return Post if found
     * @throws Exception if post not found
     */
    Post getPost(GetPostInputData getPostInputData) throws Exception;

    List<Post> getAllPosts();

    void switchToPostView();

    void switchToHomePageView();

    void switchToCreateCommentView(PostState state);

    void switchBack(CreateCommentViewModel createCommentViewModel);
}
