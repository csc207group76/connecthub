package entity;

import java.util.Objects;

/**
 * Holds contents of an forum entry. This is an abstract class.
 */
public abstract class Content {
    private String body;
    private String attachmentPath;
    private String fileType;

    /**
     * Stores information of contents.
     * @param body - the text contents itself.
     * @param attachmentPath - the path to the attachment.
     * @param fileType - the type of file the attachment.
     */
    public Content(String body, String attachmentPath, String fileType) {
        this.body = body;
        this.attachmentPath = attachmentPath;
        this.fileType = fileType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Reference equality
        if (o == null || getClass() != o.getClass()) return false; // Null or different class
        Content content = (Content) o;
        return Objects.equals(body, content.body) &&
                Objects.equals(attachmentPath, content.attachmentPath) &&
                Objects.equals(fileType, content.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, attachmentPath, fileType);
    }
}
