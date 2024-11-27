package app;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Connects to the database.
 */
public final class Repositories {
    private MongoClient mongoClient;
    private MongoCollection<Document> userRepository;
    private MongoCollection<Document> postRepository;
    private MongoCollection<Document> commentRepository;

    public Repositories() {
        // Connecting to the database
        Dotenv dotenv = Dotenv.configure().load();
		String connectionString = dotenv.get("MONGO_DB_CONNECTION_STRING");
        System.out.println(connectionString);

		ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

		MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        try {
            // Create a new client and connect to the server
            this.mongoClient = MongoClients.create(settings);

            // Send a ping to confirm a successful connection
            MongoDatabase database = this.mongoClient.getDatabase("ConnectHub");

            this.userRepository = database.getCollection("Users");
            this.postRepository = database.getCollection("Posts");
            this.commentRepository = database.getCollection("Comments");

            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
	}

    public void closeDatabaseConnection() {
        if (this.mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoCollection<Document> getUserRepository() {
        return this.userRepository;
    }

    public MongoCollection<Document> getPostRepository() {
        return this.postRepository;
    }

    public MongoCollection<Document> getCommentRepository() {
        return this.commentRepository;
    }
}
