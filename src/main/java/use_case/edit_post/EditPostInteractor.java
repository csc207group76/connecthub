package use_case.edit_post;

import entity.Post;
import entity.User;

public class EditPostInteractor implements EditPostInputBoundary {

    private EditPostDataAccess editPostDB;  // Interface to access data (edit post in DB)
    private EditPostOutputBoundary editPostOutput;  // Interface to handle output (views for success/fail)

    // Constructor to initialize dependencies
    public EditPostInteractor(EditPostDataAccess editPostDB, EditPostOutputBoundary editPostOutput) {
        this.editPostDB = editPostDB;
        this.editPostOutput = editPostOutput;
    }

    // Implementing editPost method from EditPostInputBoundary
    @Override
    public void editPost(EditPostInputData editPostInputData) {
        try {
            // Check if the user can edit the post
            if (canEdit(editPostInputData.getEditor())) {
                // If user can edit, attempt to edit the post in the database
                editPostDB.editPost(new Post(editPostInputData));  // Assuming Post constructor takes EditPostInputData
                editPostOutput.prepareSuccessView();  // Prepare the success view
            } else {
                // If user cannot edit, prepare the failure view
                editPostOutput.prepareFailView();
                System.out.println("User does not have permission to edit this post.");
            }
        } catch (EditPostFailed e) {
            // If an error occurs, prepare the failure view
            editPostOutput.prepareFailView();
            System.err.println("Error: " + e.getMessage());  // Log the error message
        }
    }

    // Implementing canEdit method from EditPostInputBoundary
    @Override
    public boolean canEdit(User user) {
        // Implement logic to check if the user can edit the post
        // For example, check if the user is the author of the post or has admin privileges
        return user.hasEditPermission();  // This is a placeholder logic
    }
}