package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for creating a comment.
 */
public class CommentFactory {
    /**
     * Creates a comment.
     * See Comment and ForumEntry for the meaning of the parameters.
     * @return the Comment object created.
     */
    public Comment createComment(User author, String content, String attachmentPath, String fileType) {
        Content commentContent = new CommentContent(content, attachmentPath, fileType);
        String entryID = generateID();
        List<Comment> replies = new ArrayList<Comment>();
        Comment comment = new Comment(entryID, author, commentContent, LocalDateTime.now(), null, 
                                0, 0, replies);
        return comment;
    }

    private String generateID() {
        // TODO same as PostFactory, maybe they could share a util function
        return "";
    }
}
