package use_case.CreateComment;

import entity.Comment;

public interface CreateCommentDataAccessInterface {

    /**
     * Checks if the given comment exists.
     * @param commentId the Id to look for.
     * @return true if the comment by this Id exists; false otherwise.
     */
    // TODO :check if our implementation of comment works for this as in we
    //  will have to access the database to find this
    boolean existsById(String commentId);


    void save(Comment comment);
}
