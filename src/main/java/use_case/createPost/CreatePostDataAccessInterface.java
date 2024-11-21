package use_case.createPost;

import entity.Post;

public interface CreatePostDataAccessInterface {

    /**
     * Creates a post.
     *
     * @param post The post to be created.
     */
    void create(Post post);

    /**
     * Checks if a post with the given title already exists.
     *
     * @param title The title to check.
     * @return True if a post with the title exists, false otherwise.
     */
    boolean existsByTitle(String title);


}

