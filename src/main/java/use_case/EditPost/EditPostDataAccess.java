package use_case.EditPost;

import entity.Post;

public interface EditPostDataAccess {

    // Method to edit a post
    void editPost(Post post) throws EditPostFailed;
}
