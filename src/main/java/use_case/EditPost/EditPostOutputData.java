package use_case.EditPost;

import entity.Content;
import entity.User;

import java.time.LocalDateTime;

public class EditPostOutputData {

    private String entryID;
    private User editor;
    private Content content;
    private LocalDateTime editDate;
    private String postTitle;
    private Content postContent;
    private String category;
    private boolean editSuccessful;

    // Constructor to initialize all fields
    public EditPostOutputData(String entryID, User editor, Content content, LocalDateTime editDate,
                              String postTitle, Content postContent, String category, boolean editSuccessful) {
        this.entryID = entryID;
        this.editor = editor;
        this.content = content;
        this.editDate = editDate;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.category = category;
        this.editSuccessful = editSuccessful;
    }

    // Setters
    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostContent(Content postContent) {
        this.postContent = postContent;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEditSuccessful(boolean editSuccessful) {
        this.editSuccessful = editSuccessful;
    }}