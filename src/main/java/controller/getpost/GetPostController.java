package controller.getpost;

import exception.PostNotFoundException;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInputData;

/**
 * Controller for the Get Post Use Case.
 */
public class GetPostController {
    private final GetPostInputBoundary getPostInputBoundary;

    public GetPostController(GetPostInputBoundary getPostInputBoundary) {
        this.getPostInputBoundary = getPostInputBoundary;
    }

    /**
     * Executes the Get Post Use Case.
     * @param entryID the ID of the post to get
     * @throws Exception if an error occurs during retrieval
     * @throws IllegalArgumentException if a null entryID is given
     */
    public void execute(String entryID) throws Exception {
        if (entryID == null || entryID.isEmpty()) {
            throw new IllegalArgumentException("Entry ID cannot be null or empty.");
        }

        final GetPostInputData getPostInputData = new GetPostInputData(entryID);

        try {
            getPostInputBoundary.getPost(getPostInputData);
        }
        catch (PostNotFoundException ex) {
            throw new Exception("Post not found for the given entry ID: " + entryID, ex);
        }
    }

}
