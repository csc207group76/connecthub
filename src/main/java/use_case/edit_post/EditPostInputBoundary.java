package use_case.edit_post;


import entity.User;

public interface EditPostInputBoundary {

    // Method to edit a post by passing EditPostInputData
    void editPost(EditPostInputData editPostInputData);

    // Method to check if the user has permission to edit a post
    boolean canEdit(User user);
}