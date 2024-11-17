package use_case.createPost;

import entity.Post;
import entity.PostContent;

public interface CreatePostDataAccessInterface {

    /**
     * Creates a post.
     *
     * @param post The post to be created.
     */
    void create(Post post);
}