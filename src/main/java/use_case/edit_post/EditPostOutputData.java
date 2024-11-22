package use_case.edit_post;

import entity.Content;

import java.time.LocalDateTime;

public class EditPostOutputData {

    private String entryID;
    private String editor;
    private Content content;
    private LocalDateTime editDate;
    private String postTitle;
    private Content postContent;
    private String category;
    private boolean editSuccessful;

    // Constructor to initialize all fields
    public EditPostOutputData(String entryID, String editor, Content content, LocalDateTime editDate,
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

    public String getEntryID() {
        return entryID;
    }

    public String getEditor() {
        return editor;
    }

    public Content getContent() {
        return content;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public Content getPostContent() {
        return postContent;
    }

    public String getCategory() {
        return category;
    }

    public boolean isEditSuccessful() {
        return editSuccessful;
    }

    
}