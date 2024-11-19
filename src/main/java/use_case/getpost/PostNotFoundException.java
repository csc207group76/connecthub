package use_case.getpost;

/**
 * Exception thrown when a post cannot be found by its entryID.
 */
public class PostNotFoundException extends Exception {
    public PostNotFoundException(String entryID) {
        super("Post with entryID " + entryID + " not found.");
    }
}
