package entity;

/**
 * Represents contents in a comment.
 */
public class CommentContent extends Content {
    /**
     * Creates a comment content object.
     * See Content class for the meaning of the parameters.
     */
    public CommentContent(String body, String attachmentPath, String fileType) {
        super(body, attachmentPath, fileType);
    }
}
