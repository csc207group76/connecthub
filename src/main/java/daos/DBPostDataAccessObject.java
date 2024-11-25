package daos;

import entity.Post;
import use_case.create_post.CreatePostDataAccessInterface;
import use_case.delete_post.DeletePostDataAccessInterface;
import use_case.getpost.GetPostDataAccessInterface;
import use_case.edit_post.EditPostDataAccessInterface;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;  

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB implementation of the DAO for storing user data. 
 */
public class DBPostDataAccessObject implements CreatePostDataAccessInterface,
                                               DeletePostDataAccessInterface,
                                               EditPostDataAccessInterface,
                                               GetPostDataAccessInterface {
    private final String ENTRY_ID = "post_id";
    private final String AUTHOR = "author";
    private final String CONTENT_BODY = "content_body";
    private final String ATTACHMENT_PATH = "attachment_path";
    private final String FILE_TYPE = "file_type";
    private final String POST_TITLE = "title";
    private final String CATEGORY = "category";
    private final String POSTED_DATE = "posted_date";
    private final String LAST_MODIFIED = "last_modified";
    private final String LIKES = "likes";
    private final String DISLIKES = "dislikes";
    private final String COMMENTS = "comments";

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private MongoCollection<Document> postRepository;

    public DBPostDataAccessObject(MongoCollection<Document> postRepository) {
        this.postRepository = postRepository;
    }

    // TODO check if there's any other operations missing
    @Override
    public boolean existsByID(String postId) {
        return this.getPostByEntryID(postId) != null;
    }

    @Override
    public void createPost(Post post) {
        this.insertPostToDB(post);
    }

    @Override
    public JSONObject getPostByEntryID(String id) {
        return new JSONObject(queryOnePostBy(ENTRY_ID, id).toJson());
    }

    // TODO used for filtering posts, not implemented yet
    // @Override
    // public List<JSONObject> getPostsByCategory(String category) {
    //     List<JSONObject> posts = new ArrayList<>();
    //     MongoCursor<Document> retrievedPosts = this.queryMultiplePostsBy(CATEGORY, category);

    //     try {
    //         while (retrievedPosts.hasNext()) {
    //             String jsonStr = retrievedPosts.next().toJson();
    //             posts.add(new JSONObject(jsonStr));
    //         }
    //     } finally {
    //         retrievedPosts.close();
    //     }
    // }

    @Override
    public List<JSONObject> getAllPostsByUserID(String userID) {
        return null;
    }

    @Override
    public List<JSONObject> getAllPosts() {
        List<JSONObject> res = new ArrayList<>();

        FindIterable<Document> posts = this.postRepository.find();
        for (Document post : posts) {
            res.add(new JSONObject(post.toJson()));
        }

        return res;
    }

    // @Override
    // public List<Post> getPostsByTime(int postSize) { // TODO figure out the time stamp if we want this method
    //     return null;
    // }

   @Override
    public void deletePost(String postID) {
        Bson query = eq(ENTRY_ID, postID);
        
        try {
            this.postRepository.deleteOne(query);
        } catch (MongoException error) {
            // TODO throw some error, depending how the rest of the group implemts stuff.
        }
    }

    @Override
    public void updatePost(Post updatedContent) {
        Document query = new Document().append(ENTRY_ID, updatedContent.getEntryID());

        Bson updates = Updates.combine(
            Updates.set(CONTENT_BODY, updatedContent.getContent().getBody()),
            Updates.set(ATTACHMENT_PATH, updatedContent.getContent().getAttachmentPath()),
            Updates.set(FILE_TYPE, updatedContent.getContent().getFileType()),
            Updates.set(CATEGORY, updatedContent.getCategory()),
            Updates.set(LAST_MODIFIED, updatedContent.getLastModifiedDate()),
            Updates.set(LIKES, updatedContent.getLikes()),
            Updates.set(DISLIKES, updatedContent.getDislikes()),
            Updates.set(COMMENTS, updatedContent.getComments()) // TODO convert to appropriate type if error
        );

        // Instructs the driver to insert a new document if none match the query
        UpdateOptions insertNewDoc = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = this.postRepository.updateOne(query, updates, insertNewDoc);
            System.out.println("Upserted id: " + result.getUpsertedId());
        } catch (MongoException error) {
            // throw err?
        }
    }

    
    /**
     * Inserts the given post into the database.
     * @param user - a user in the application.
     */
    private void insertPostToDB(Post post) {
        try {
            
            Document data = (new Document()
                .append(ENTRY_ID, post.getEntryID())
                .append(AUTHOR, post.getAuthor())
                .append(CONTENT_BODY, post.getContent().getBody())
                .append(ATTACHMENT_PATH, post.getContent().getAttachmentPath())
                .append(FILE_TYPE, post.getContent().getFileType())
                .append(POST_TITLE, post.getPostTitle())
                .append(CATEGORY, post.getCategory())
                .append(POSTED_DATE, post.getPostedDate().toString())
                .append(LAST_MODIFIED, post.getLastModifiedDate().toString())
                .append(LIKES, post.getLikes())
                .append(DISLIKES, post.getDislikes())
                .append(COMMENTS, post.getComments()) // TODO figure out type conversions if neccessary
            );

            InsertOneResult result = this.postRepository.insertOne(data);
            System.out.println("Successfully inserted post with insert id: " + result.getInsertedId());
        } catch (MongoException err) {
            // TODO throws custom exceptions when they're created
        }
    }

    /**
     * Queries a specific post from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private Document queryOnePostBy(String field, String target) {
        Document doc = this.postRepository
            .find(eq(field, target))
            .first();

        return doc;
    }

    /**
     * Queries multiple posts with given filters from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private MongoCursor<Document> queryMultiplePostsBy(String field, String target) {
        MongoCursor<Document> cursor = this.postRepository.find(lt(field, target))
                .sort(Sorts.descending(POSTED_DATE)).iterator();
        
        return cursor;
    }
}
