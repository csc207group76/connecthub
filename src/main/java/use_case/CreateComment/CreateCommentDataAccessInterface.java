package use_case.CreateComment;

import entity.NormalComment;

public interface CreateCommentDataAccessInterface {

    /**
     * Checks if the given comment exists.
     * @param commentId the Id to look for
     * @return true if the comment by this Id exists; false otherwise
     */
    boolean existsById(String commentId);


    void save(NormalComment comment);
}
