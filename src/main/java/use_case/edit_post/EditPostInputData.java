package use_case.edit_post;

import entity.Content;
import entity.User;

import java.time.LocalDateTime;

public class EditPostInputData {

    private String entryID;
    private User editor;
    private Content content;
    private LocalDateTime editDate;
    private String postTitle;
    private Content postContent;
    private String category;

    // Constructor to initialize all fields
    public EditPostInputData(String entryID, User editor, Content content, LocalDateTime editDate,
                             String postTitle, Content postContent, String category) {
        this.entryID = entryID;
        this.editor = editor;
        this.content = content;
        this.editDate = editDate;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.category = category;
    }

    public String getEntryID() {
        return entryID;
    }

    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Content getPostContent() {
        return postContent;
    }

    public void setPostContent(Content postContent) {
        this.postContent = postContent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
