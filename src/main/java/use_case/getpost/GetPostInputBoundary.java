package use_case.getpost;

import entity.Post;

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
}
