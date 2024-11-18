package daos;

import entity.Comment;
import entity.Post;
import entity.PostContent;
import entity.PostFactory;
// TODO rename to whatever package/class name created by others
import use_case.create_comment.CreateCommentDataAccessInterface;
import use_case.delete_comment.DeleteCommentDataAccessInterface;
import use_case.update_comment.UpdateCommentDataAccessInterface;
import use_case.get_comment.GetCommentDataAccessInterface;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;  

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB implementation of the DAO for storing user data. 
 */
public class DBCommentDataAccessObject implements CreateCommentDataAccessInterface,
                                                  DeleteCommentDataAccessInterface,
                                                  UpdateCommentDataAccessInterface,
                                                  GetCommentDataAccessInterface {
    private final String ENTRY_ID = "comment_id";
    private final String COMMENTER = "commenter";
    private final String CONTENT_BODY = "content_body";
    private final String ATTACHMENT_PATH = "attachment_path";
    private final String FILE_TYPE = "file_type";
    private final String POSTED_DATE = "posted_date";
    private final String LAST_MODIFIED = "last_modified";
    private final String LIKES = "likes";
    private final String DISLIKES = "dislikes";
    private final String REPLIES = "replies";

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private MongoCollection<Document> commentRepository;

    public DBCommentDataAccessObject(MongoCollection<Document> commentRepository) {
        this.commentRepository = commentRepository;
    }

    // TODO check if there's any other operations missing

    @Override
    public void createComment(Comment comment) {
        this.insertCommentToDB(comment);
    }

    @Override
    public JSONObject getCommentByID(String id) {
        return new JSONObject(queryOneCommentBy(ENTRY_ID, id).toJson());
    }

    @Override 
    public void deleteComment(String commentID) {
        Bson query = eq(ENTRY_ID, commentID);
        
        try {
            this.commentRepository.deleteOne(query);
        } catch (MongoException error) {
            // TODO throw some error, depending how the rest of the group implemts stuff.
        }
    }

    @Override
    public void updateComment(Comment updatedComment) {
        Document query = new Document().append(ENTRY_ID, updatedComment.getEntryID());

        Bson updates = Updates.combine(
            Updates.set(CONTENT_BODY, updatedComment.getContent().getBody()),
            Updates.set(ATTACHMENT_PATH, updatedComment.getContent().getAttachmentPath()),
            Updates.set(FILE_TYPE, updatedComment.getContent().getFileType()),
            Updates.set(LAST_MODIFIED, updatedComment.getLastModifiedDate()),
            Updates.set(LIKES, updatedComment.getLikes()),
            Updates.set(DISLIKES, updatedComment.getDislikes()),
            Updates.set(REPLIES, updatedComment.getReplies()) // TODO type conversion? need testing
        );

        // Instructs the driver to insert a new document if none match the query
        UpdateOptions insertNewDoc = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = this.commentRepository.updateOne(query, updates, insertNewDoc);
            System.out.println("Upserted id: " + result.getUpsertedId());
        } catch (MongoException error) {
            // throw err?
        }
    }

    
    /**
     * Inserts the given post into the database.
     * @param user - a user in the application.
     */
    private void insertPostToDB(Comment comment) {
        try {
            
            Document data = (new Document()
                .append(ENTRY_ID, comment.getEntryID())
                .append(CONTENT_BODY, comment.getContent().getBody())
                .append(ATTACHMENT_PATH, comment.getContent().getAttachmentPath())
                .append(FILE_TYPE, comment.getContent().getFileType())
                .append(POSTED_DATE, comment.getPostedDate())
                .append(LAST_MODIFIED, comment.getLastModifiedDate())
                .append(LIKES, comment.getLikes())
                .append(DISLIKES, comment.getDislikes())
                .append(REPLIES, comment.getReplies()) // TODO figure out type conversions if neccessary
            );

            InsertOneResult result = this.commentRepository.insertOne(data);
            System.out.println("Successfully inserted post with insert id: " + result.getInsertedId());
        } catch (MongoException err) {
            // TODO throws custom exceptions when they're created
        }
    }

    /**
     * Queries a specific comment from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private Document queryOnePostBy(String field, String target) {
        Bson projectionFields = Projections.fields(
            Projections.include(field),
            Projections.excludeId()
        );
        
        Document doc = this.commentRepository
            .find(eq(field, target))
            .projection(projectionFields)
            .first();

        return doc;
    }

    /**
     * Queries multiple comments with given filters from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private MongoCursor<Document> queryMultiplePostsBy(String field, String target) {
        Bson projectionFields = Projections.fields(
            Projections.include(field),
            Projections.excludeId()
        );

        // Retrieves documents that match the filter, applying a projection and a descending sort to the results
        MongoCursor<Document> cursor = this.commentRepository.find(lt(field, target))
                .projection(projectionFields)
                .sort(Sorts.descending(POSTED_DATE)).iterator();
        
        return cursor;
    }
}
