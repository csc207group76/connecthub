package daos;

import entity.Comment;
import use_case.createComment.CreateCommentDataAccessInterface;
import use_case.DeleteComment.DeleteCommentDataAccessInterface;
//import use_case.get_comment.GetCommentDataAccessInterface;

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
        DeleteCommentDataAccessInterface{

    private final String ENTRY_ID = "comment_id";
    private final String COMMENTER = "commenter";
    private final String CONTENT_BODY = "content_body";
    private final String ATTACHMENT_PATH = "attachment_path";
    private final String FILE_TYPE = "file_type";
    private final String POSTED_DATE = "posted_date";

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final MongoCollection<Document> commentRepository;

    public DBCommentDataAccessObject(MongoCollection<Document> commentRepository) {
        this.commentRepository = commentRepository;
    }

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
            // throw error
        }
    }

    @Override
    public Comment existsCommentById(String commentId) {
        return null;
    }

    /**
     * Inserts the given comment into the database.
     * @param comment the comment we are trying to insert
     */
    private void insertCommentToDB(Comment comment) {
        try {
            Document data = (new Document()
                    .append(ENTRY_ID, comment.getEntryID())
                    .append(COMMENTER, comment.getAuthor())
                    .append(CONTENT_BODY, comment.getContent())
                    .append(ATTACHMENT_PATH, comment.getContent().getAttachmentPath())
                    .append(FILE_TYPE, comment.getContent().getFileType())
                    .append(POSTED_DATE, comment.getPostedDate())
            );

            InsertOneResult result = this.commentRepository.insertOne(data);
            System.out.println("Successfully inserted comment with insert id: " + result.getInsertedId());
        } catch (MongoException err) {
           // throw error
        }
    }

    /**
     * Queries a specific comment from the database.
     * @param field - the column to match.
     * @param target - the target value to query for.
     */
    private Document queryOneCommentBy(String field, String target) {
        Bson projectionFields = Projections.fields(
                Projections.include(field),
                Projections.excludeId()
        );

        return this.commentRepository
                .find(eq(field, target))
                .projection(projectionFields)
                .first();
    }

    /**
     * Queries multiple comments with given filters from the database.
     * @param field - the column to match.
     * @param target - the target value to query for.
     */
    private MongoCursor<Document> queryMultipleCommentsBy(String field, String target) {
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

    public DateTimeFormatter getDATE_TIME_FORMATTER() {
        return DATE_TIME_FORMATTER;
    }
}