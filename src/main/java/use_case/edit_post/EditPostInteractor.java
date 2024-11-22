package use_case.edit_post;

import daos.DBUserDataAccessObject;
import entity.Content;
import entity.Post;
import entity.PostContent;
import entity.User;

public class EditPostInteractor implements EditPostInputBoundary {

    private EditPostDataAccessInterface editPostDB;  // Interface to access data (edit post in DB)
    private DBUserDataAccessObject userRepo; // To get the current user
    private EditPostOutputBoundary editPostOutput;  // Interface to handle output (views for success/fail)

    // Constructor to initialize dependencies
    public EditPostInteractor(EditPostDataAccessInterface editPostDB, DBUserDataAccessObject userRepo, 
                              EditPostOutputBoundary editPostOutput) {
        this.editPostDB = editPostDB;
        this.editPostOutput = editPostOutput;
    }

    // Implementing editPost method from EditPostInputBoundary
    @Override
    public void editPost(EditPostInputData editPostInputData) {
        boolean userCanEdit = this.canEdit(editPostInputData.getEditor());

        if (!userCanEdit) {
            editPostOutput.prepareFailView("User does not have permission to edit this post.");
        } 

        Content postContent = new PostContent(editPostInputData.getEditedContent(),
                                              editPostInputData.getAttachmentPath(),
                                              editPostInputData.getFileType());

        EditPostOutputData editPostOutputData = new EditPostOutputData(
            editPostInputData.getEntryID(), 
            editPostInputData.getEditor(), 
            editPostInputData.getEditDate(), 
            editPostInputData.getPostTitle(), 
            editPostInputData.getPostContent(),
            editPostInputData.getCategory(),
            userCanEdit
        );

        editPostDB.updatePost(); 
        editPostOutput.prepareSuccessView();  
    }

    // Implementing canEdit method from EditPostInputBoundary
    @Override
    public boolean canEdit(String userId) {
        // Implement logic to check if the user can edit the post
        // For example, check if the user is the author of the post or has admin privileges
        return userId.equals(this.userRepo.getCurrentUser().getUserID());  
    }
}