package use_case.create_post;

import static org.mockito.Mockito.*;

import daos.DBUserDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CreatePostTests {

    private CreatePostInteractor createPostInteractor;
    private CreatePostDataAccessInterface mockDataAccess;
    private DBUserDataAccessObject mockUserRepo;
    private CreatePostOutputBoundary mockPresenter;
    private PostFactory mockPostFactory;
    private User mockUser;

    private CreatePostInputData inputData;
    private CreatePostOutputData outputData;

    private String author = "author@example.com";
    private String postContent = "This is a post content";
    private String attachmentPath = "path/to/attachment";
    private String fileType = "jpg";
    private int dislikes = 0;
    private int likes = 5;
    private String postTitle = "Post Title";
    private String category = "General";

    private List<Comment> comments;
    private List<User> moderators;

    @Before
    public void setup() {
        // Mock the dependencies
        mockDataAccess = mock(CreatePostDataAccessInterface.class);
        mockUserRepo = mock(DBUserDataAccessObject.class);
        mockPresenter = mock(CreatePostOutputBoundary.class);
        mockPostFactory = mock(PostFactory.class);

        // Create a mock user
        mockUser = mock(User.class);
        when(mockUserRepo.getCurrentUser()).thenReturn(mockUser);

        // Initialize the interactor with the mocked objects
        createPostInteractor = new CreatePostInteractor(mockDataAccess, mockUserRepo, mockPresenter, mockPostFactory);
    }

    @Test
    public void testCreatePost_Success() {
        // Create valid input data
        List<User> moderators = Arrays.asList(mock(User.class));
        List<Comment> comments = Arrays.asList(mock(Comment.class));

        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", "This is a post content", "path/to/attachment", "jpg", 0, 0,
                "Post Title", moderators, comments, "General"
        );

        // Mock post creation
        Post mockPost = mock(Post.class);
        when(mockPostFactory.createPost(anyString(), eq(mockUser), eq(inputData.getPostContent()),
                eq(inputData.getAttachmentPath()), eq(inputData.getFileType()),
                eq(inputData.getPostTitle()), eq(inputData.getCategory())))
                .thenReturn(mockPost);

        // Call the method
        createPostInteractor.createPost(inputData);

        // Verify that the success view was called
        verify(mockPresenter).prepareSuccessView(any(CreatePostOutputData.class));
    }

    @Test
    public void testCreatePost_MissingTitle() {
        // Create input data with an empty title
        List<User> moderators = Arrays.asList(mock(User.class));
        List<Comment> comments = Arrays.asList(mock(Comment.class));

        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", "This is a post content", "path/to/attachment", "jpg", 0, 0,
                "", moderators, comments, "General"
        );

        // Call the method and expect it to fail
        try {
            createPostInteractor.createPost(inputData);
            fail("Expected PostCreationFailedException");
        } catch (PostCreationFailedException e) {
            // Verify that the failure view was triggered with the correct message
            verify(mockPresenter).prepareFailView("Please add title!");
        }
    }

    @Test
    public void testCreatePost_MissingCategory() {
        // Create input data with a null category
        List<User> moderators = Arrays.asList(mock(User.class));
        List<Comment> comments = Arrays.asList(mock(Comment.class));

        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", "This is a post content", "path/to/attachment", "jpg", 0, 0,
                "Post Title", moderators, comments, null
        );

        // Call the method and expect it to fail
        try {
            createPostInteractor.createPost(inputData);
            fail("Expected PostCreationFailedException");
        } catch (PostCreationFailedException e) {
            // Verify that the failure view was triggered with the correct message
            verify(mockPresenter).prepareFailView("Please choose category!");
        }
    }

    @Test
    public void testCreatePost_MissingContent() {
        // Create input data with empty post content
        List<User> moderators = Arrays.asList(mock(User.class));
        List<Comment> comments = Arrays.asList(mock(Comment.class));

        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", "", "path/to/attachment", "jpg", 0, 0,
                "Post Title", moderators, comments, "General"
        );

        // Call the method and expect it to fail
        try {
            createPostInteractor.createPost(inputData);
            fail("Expected PostCreationFailedException");
        } catch (PostCreationFailedException e) {
            // Verify that the failure view was triggered with the correct message
            verify(mockPresenter).prepareFailView("Please fill in post contents!");
        }
    }

    @Before
    public void setUp() {
        // Setup mock data
        moderators = Arrays.asList(mock(User.class), mock(User.class));
        comments = Arrays.asList(mock(Comment.class), mock(Comment.class));

        inputData = new CreatePostInputData(
                author, postContent, attachmentPath, fileType, dislikes, likes, postTitle, moderators, comments, category
        );

        // For output data, we need a valid postContent object
        PostContent content = mock(PostContent.class);

        // Create mock data for CreatePostOutputData
        outputData = new CreatePostOutputData(
                "entryID", author, content, LocalDateTime.now(), LocalDateTime.now(), dislikes, likes, postTitle, comments, category, true
        );
    }

    @Test
    public void testCreatePostInputDataGetters() {
        // Test CreatePostInputData getters

        assertEquals("author@example.com", inputData.getAuthor());
        assertEquals("This is a post content", inputData.getPostContent());
        assertEquals("path/to/attachment", inputData.getAttachmentPath());
        assertEquals("jpg", inputData.getFileType());
        assertEquals(0, inputData.getDislikes());
        assertEquals(5, inputData.getLikes());
        assertEquals("Post Title", inputData.getPostTitle());
        assertEquals(moderators, inputData.getModerators());
        assertEquals(comments, inputData.getComments());
        assertEquals("General", inputData.getCategory());
    }

    @Test
    public void testCreatePostOutputDataGetters() {
        // Test CreatePostOutputData getters

        assertEquals("entryID", outputData.getEntryID());
        assertEquals("author@example.com", outputData.getAuthor());
        assertNotNull(outputData.getContent());  // This checks that the content is not null
        assertNotNull(outputData.getPostedDate());
        assertNotNull(outputData.getLastModifiedDate());
        assertEquals(0, outputData.getDislikes());
        assertEquals(5, outputData.getLikes());
        assertEquals("Post Title", outputData.getPostTitle());
        assertEquals(comments, outputData.getComments());
        assertEquals("General", outputData.getCategory());
        assertTrue(outputData.isCreationSuccessful());
    }
}



//public class CreatePostTests {
//
//    @Test
//    public void successPostCreationTest() {
//        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();// For user signup
//        // For post creation
//
//        // creating a user
//        User user = new CommonUserFactory().create("izabelle", "1234", "abc", "12/12/12", "Izabelle marianne", "izabelle@gmail.com", new ArrayList<>(), new ArrayList<>());
//        userRepository.save(user);
//        // check they have been saved
//        assertTrue(userRepository.existsByUsername("izabelle"));
//        // now create the post
//        InMemoryPostDataAccessObject postRepository = new InMemoryPostDataAccessObject(userRepository);
//        PostContent content = new PostContent("hello world!", "excel", "text");
//        CreatePostInputData inputData = new CreatePostInputData("izabelle",
//                "hello!",
//                "String attachmentPath",
//                "String fileType",
//       0,
//        0,
//        "first!",
//        new ArrayList<>(),
//        new ArrayList<>(),
//        "sports");
//
////        // Input data for creating a post
////        Post post = new Post("1234", user , content , LocalDateTime.now(),
////                LocalDateTime.now(),0, 0, "First post!",
////                new ArrayList<>(), "sports");
//
//        // Mock presenter
//        CreatePostOutputBoundary successPresenter = new CreatePostOutputBoundary() {
//            @Override
//            public void prepareSuccessView(CreatePostOutputData outputData) {
//                // Check output data is as expected
//                assertEquals("1234", outputData.getEntryID());
//                assertEquals(content.getBody(), outputData.getContent().getBody());
//                assertEquals(user.getUserID(), outputData.getAuthor());
//                // Ensure the post is saved in the repository
//                assertTrue(postRepository.existsByTitle("First post!"));
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        // Create the interactor and execute the use case
//        PostFactory postFactory = new PostFactory();
//        CreatePostInputBoundary interactor = new CreatePostInteractor( postRepository, userRepository, successPresenter, postFactory);
//        interactor.createPost(inputData);
//
//
////        assertTrue(postRepository.existsByTitle("First post!"));
////        Post createdPost = postRepository.getPostByTitle("First post!");
////        assertNotNull(createdPost);
////        assertEquals("hello world!", createdPost.getContent().getBody());
////        assertEquals("izabelle", createdPost.getAuthor().getName());
//    }
//}



