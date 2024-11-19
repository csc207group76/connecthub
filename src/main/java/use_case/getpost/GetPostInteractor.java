package use_case.getpost;

import entity.Post;
import exception.PostNotFoundException;

/**
 * The Get Post Interactor.
 */
public class GetPostInteractor implements GetPostInputBoundary {
    private final GetPostInputData getPostInputData;
    private final GetPostDataAccessInterface postDB;
    private final GetPostOutputBoundary getPostPresenter;

    public GetPostInteractor(GetPostInputData getPostInputData,
                             GetPostDataAccessInterface postDB,
                             GetPostOutputBoundary getPostPresenter) {
        this.getPostInputData = getPostInputData;
        this.postDB = postDB;
        this.getPostPresenter = getPostPresenter;
    }

    @Override
    public Post getPost(GetPostInputData postInputData) throws IllegalArgumentException {
        final String entryID = postInputData.getEntryID();

        if (entryID == null) {
            throw new IllegalArgumentException();
        }

        try {
            final Post retrievedPost = postDB.getPostByEntryID(entryID);
            final GetPostOutputData retrievedPostOutputData = new GetPostOutputData(
                    retrievedPost.getEntryID(),
                    retrievedPost.getContent());
            getPostPresenter.prepareSuccessView(retrievedPostOutputData);
            return retrievedPost;
        }
        catch (Exception ex) {
            getPostPresenter.prepareFailView(ex.getMessage());
            return null;
        }
    }
}
