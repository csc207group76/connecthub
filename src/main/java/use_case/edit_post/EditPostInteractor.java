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
    private EditPostDataAccessInterface postDB;
    private EditPostOutputBoundary presenter;
    private DBUserDataAccessObject userRepo;

    // Constructor that requires all three dependencies
    public EditPostInteractor(EditPostDataAccessInterface postDB, EditPostOutputBoundary presenter, DBUserDataAccessObject userRepo) {
        this.postDB = postDB;
        this.presenter = presenter;
        this.userRepo = userRepo;  // Inject the userRepo
    }

    // Implementing editPost method from EditPostInputBoundary
    @Override
    public void editPost(EditPostInputData editPostInputData) throws EditPostFailed {
        boolean userCanEdit = this.canEdit(editPostInputData.getEditor());

        if (!userCanEdit) {
            presenter.prepareFailView("User does not have permission to edit this post.");
            throw new EditPostFailed("You do not have permission to edit this post.");
        }

        JSONObject postData = this.postDB.getPostByEntryID(editPostInputData.getEntryID());
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

        postDB.updatePost(post);
        presenter.prepareSuccessView(editPostOutputData);
    }

    // Implementing canEdit method from EditPostInputBoundary
    @Override
    public boolean canEdit(String userId) {
        String currentUserId = this.userRepo.getCurrentUser().getUserID();
        return userId.equals(currentUserId);
    }

    public Post jsonToPost(JSONObject postData) {
        try {
            Content postContent = new PostContent(postData.optString("content_body", ""),
                    postData.optString("attachment_path", ""),
                    postData.optString("file_type", ""));

            JSONArray commentData = postData.optJSONArray("comments");
            List<Comment> comments = new ArrayList<>();
            if (commentData != null) {
                for (int i = 0; i < commentData.length(); i++) {
                    comments.add(null); // TODO: Replace with actual Comment parsing
                }
            }

            return new Post(
                    postData.optString("post_id", ""),
                    postData.optString("author", ""),
                    postContent,
                    LocalDateTime.parse(postData.optString("posted_date", "1970-01-01T00:00:00")),
                    LocalDateTime.parse(postData.optString("last_modified", "1970-01-01T00:00:00")),
                    postData.optInt("likes", 0),
                    postData.optInt("dislikes", 0),
                    postData.optString("title", ""),
                    comments,
                    postData.optString("category", "")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse post data.", e);
        }
    }
}