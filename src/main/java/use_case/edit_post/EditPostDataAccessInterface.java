package use_case.edit_post;

import org.json.JSONObject;

import entity.Post;

public interface EditPostDataAccessInterface {

    void updatePost(Post post);

    JSONObject getPostByID(String postID);
}
