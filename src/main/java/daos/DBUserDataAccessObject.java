package daos;

import entity.User;
import use_case.signup.SignupDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import static com.mongodb.client.model.Filters.eq;

/**
 * MongoDB implementation of the DAO for storing user data. 
 */
public class DBUserDataAccessObject implements SignupDataAccessInterface,
                                               LoginDataAccessInterface,
                                               LogoutDataAccessInterface {
    private final String USER_ID = "userId";
    private final String USER_NAME = "username";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String BIRTH_DATE = "birth_date";
    private final String FULL_NAME = "full_name";
    private final String MODERATING = "moderating";
    private final String POSTS = "posts";

    private MongoCollection<Document> userRepository;
    private User currentUser;

    public DBUserDataAccessObject(MongoCollection<Document> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        this.insertUserToDB(user);
    }
    
    @Override
    public boolean existsByID(String userID) {
        return this.queryOneUserBy(USER_ID, userID) != null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.queryOneUserBy(USER_NAME, username) != null;
    }


    @Override
    public boolean existsByEmail(String email) {
        return this.queryOneUserBy(EMAIL, email) != null;
    }

    @Override
    public JSONObject getUserById(String userID) {
        return new JSONObject(this.queryOneUserBy(USER_ID, userID).toJson());
    }
    
    @Override
    public JSONObject getUserByUsername(String username) {
        return new JSONObject(this.queryOneUserBy(USER_NAME, username).toJson());
    }

    @Override
    public JSONObject getUserByEmail(String email) {
        return new JSONObject(this.queryOneUserBy(EMAIL, email).toJson());
    }
    
    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override 
    public void logoutUser() {
        this.setCurrentUser(null);
    }

    /**
     * Inserts the given user into the database.
     * @param user - a user in the application.
     */
    private void insertUserToDB(User user) {
        try {
            Document data = new Document()
                .append(USER_ID, user.getUserID())
                .append(USER_NAME, user.getUsername())
                .append(PASSWORD, user.getPassword())
                .append(EMAIL, user.getEmail())
                .append(BIRTH_DATE, user.getBirthDate())
                .append(FULL_NAME, user.getFullName())
                .append(MODERATING, user.getModerating())
                .append(POSTS, user.getPosts());

            InsertOneResult result = this.userRepository.insertOne(data);
            System.out.println("Successfully inserted user with insert id: " + result.getInsertedId());
        } catch (MongoException err) {
            // TODO throws custom exceptions when they're created
        }
    }

    /**
     * Queries a specific user from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private Document queryOneUserBy(String field, String target) {
        Document doc = this.userRepository
            .find(eq(field, target))
            .first();

        return doc;
    }
}
