package use_case.edit_post;

import entity.Content;

import java.time.LocalDateTime;

public class EditPostInputData {

    private String entryID;
    private String editor;
    private String editedContent;
    private String attachmentPath;
    private String fileType;
    private LocalDateTime editDate;
    private String postTitle;
    private Content postContent;
    private String category;

    // Constructor to initialize all fields
    public EditPostInputData(String entryID, String editor, String editedContent, String attachmentPath,
                             String fileType, String postTitle, String category) {
        this.entryID = entryID;
        this.editor = editor;
        this.editedContent = editedContent;
        this.attachmentPath = attachmentPath;
        this.fileType = fileType;
        this.editDate = LocalDateTime.now();
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.category = category;
    }

    public String getEntryID() {
        return entryID;
    }

    public String getEditor() {
        return editor;
    }

    public String getEditedContent() {
        return editedContent;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public String getFileType() {
        return fileType;
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
}
