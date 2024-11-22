package use_case.edit_post;


public interface EditPostInputBoundary {

    // Method to edit a post by passing EditPostInputData
    void editPost(EditPostInputData editPostInputData) throws EditPostFailed;

    // Method to check if the user has permission to edit a post
    boolean canEdit(String userId);
}