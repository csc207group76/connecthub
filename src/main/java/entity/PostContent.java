package entity;

/**
 * Represents contents in a post.
 */
public class PostContent extends Content {
    /**
     * Creates a post content object.
     * See Content class for the meaning of the parameters.
     */
    public PostContent(String body, String attachmentPath, String fileType) {
        super(body, attachmentPath, fileType);
    }
}
