package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import daos.DBUserDataAccessObject;
import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInputData;
import use_case.create_post.PostCreationFailedException;
import use_case.getpost.GetPostInputBoundary;
import entity.Post;
import entity.User;
import entity.Comment;

@RestController
public class PostController {
    private final GetPostInputBoundary getPostInteractor;
    private final CreatePostInputBoundary createPostInteractor;
    private final DBUserDataAccessObject userDAO;

    public PostController(DBUserDataAccessObject userDAO, // Change when there's a get user use case
                          GetPostInputBoundary getPostInteractor,
                          CreatePostInputBoundary createPostInteractor) {
        this.getPostInteractor = getPostInteractor;
        this.createPostInteractor = createPostInteractor;
        this.userDAO = userDAO;
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        // The posts should be paginated in the service. This is a temporary solution.
        List<Post> allPosts = getPostInteractor.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PostMapping("/create-post")
    public ResponseEntity<String> createPost(@RequestBody Map<String, Object> requestBody) {
        User currentUser = this.userDAO.getCurrentUser();
        String currentUserID = currentUser == null ? null : currentUser.getUserID();

        final CreatePostInputData createPostInputData = new CreatePostInputData(
            currentUserID,
            (String) requestBody.get("content"),
            (String) requestBody.get("attachment_path"),
            (String) requestBody.get("file_type"),
            0,
            0,
            (String) requestBody.get("post_title"),
            new ArrayList<User>(), // TODO temp sol, need changes in the use case: don't have too much time to refactor
            new ArrayList<Comment>(),
            (String) requestBody.get("category")
        );

        try {
            this.createPostInteractor.createPost(createPostInputData);
            return ResponseEntity.status(HttpStatus.OK).body("Post Created!");
        } catch (PostCreationFailedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
        }
    }

}
