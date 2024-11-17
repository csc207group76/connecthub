package use_case.createPost;

import use_case.signup.SignupInputData;

public interface CreatePostInputBoundary {

    /**
     * Executes the signup use case.
     * @param createPostInputData the input data
     */
    void createPost(CreatePostInputData createPostInputData);

}
