package use_case.edit_post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import daos.DBUserDataAccessObject;
import entity.Comment;
import entity.Content;
import entity.Post;
import entity.PostContent;

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
    public void editPost(EditPostInputData editPostInputData) throws EditPostFailed {
        boolean userCanEdit = this.canEdit(editPostInputData.getEditor());

        if (!userCanEdit) {
            editPostOutput.prepareFailView("User does not have permission to edit this post.");
            throw new EditPostFailed("You do not have permission to edit this post.");
        } 

        JSONObject postData = this.editPostDB.getPostByID(editPostInputData.getEntryID());
        Post post = this.jsonToPost(postData);

        Content updatedContent = new PostContent(editPostInputData.getEditedContent(),
                                              editPostInputData.getAttachmentPath(),
                                              editPostInputData.getFileType());

        post.setContent(updatedContent);
        post.setEditDate(editPostInputData.getEditDate());
        post.setPostTitle(editPostInputData.getPostTitle());
        post.setCategory(editPostInputData.getCategory());

        EditPostOutputData editPostOutputData = new EditPostOutputData(
            editPostInputData.getEntryID(), 
            editPostInputData.getEditor(), 
            editPostInputData.getEditDate(), 
            editPostInputData.getPostTitle(), 
            editPostInputData.getPostContent(),
            editPostInputData.getCategory(),
            userCanEdit
        );

        editPostDB.updatePost(post); 
        editPostOutput.prepareSuccessView(editPostOutputData);  
    }

    // Implementing canEdit method from EditPostInputBoundary
    @Override
    public boolean canEdit(String userId) {
        // Implement logic to check if the user can edit the post
        // For example, check if the user is the author of the post or has admin privileges
        return userId.equals(this.userRepo.getCurrentUser().getUserID());  
    }

    private Post jsonToPost(JSONObject postData) {
        Content postContent = new PostContent(postData.getString("content_body"),
                                              postData.getString("attachment_path"),
                                              postData.getString("file_type"));

        JSONArray commentData = postData.getJSONArray("comments");
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentData.length(); i++){ 
            // TODO modify when implementing the comment feature. Will likely need to change the DAO implementation
            // Comment comment = new Comment(); 
            comments.add(null);
        } 

        Post post = new Post(
            postData.getString("post_id"), 
            postData.getString("author"),
            postContent,
            LocalDateTime.parse(postData.getString("posted_date")),
            LocalDateTime.parse(postData.getString("last_modified")),
            postData.getInt("likes"),
            postData.getInt("dislikes"),
            postData.getString("title"),
            comments,
            postData.getString("category")
        );

        return post;
    }
}