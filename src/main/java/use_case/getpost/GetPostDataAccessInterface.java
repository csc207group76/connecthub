package use_case.getpost;

import java.util.List;

import org.json.JSONObject;

/**
 * DAO for the Get Post Use Case.
 */
public interface GetPostDataAccessInterface {

    /**
     * Retrieve the post with a specific entryID.
     * @param entryID the entryID of the Post to retrieve
     * @return the Post object with the given entryID
     * @throws PostNotFoundException if a post with the given entryID does not exist
     */
    JSONObject getPostByEntryID(String entryID) throws PostNotFoundException;

    /**
     * Retrieve all posts made by a given user  post with a specific entryID.
     * @param userID the userID of the user to retreive posts
     * @return a list of Post json data of the given user
     */
    List<JSONObject> getAllPostsByUserID(String userID);

    /**
     * Retrieve all posts in the database.
     * @return a list of post json data
     */
    List<JSONObject> getAllPosts();
}
