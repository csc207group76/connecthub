package use_case.deletePost;

import entity.Post;

public interface DeletePostDataAccessInterface {

    /**
     * Deletes a post using ID.
     *
     * @param postID The ID of the post
     */
    void deleteComment(String postID);

    /**
     * Finds post by its ID
     *
     * @param postID The ID of the post
     * @return The post if found, or null if not.
     */
    Post existsCommentById(String postID);
}
