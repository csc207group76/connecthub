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

    @Test
    public void testCreatePost_NoCurrentUser() {
        when(mockUserRepo.getCurrentUser()).thenReturn(null);

        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", "This is a post content", "path/to/attachment", "jpg", 0, 0,
                "Post Title", moderators, comments, "General"
        );

        try {
            createPostInteractor.createPost(inputData);
            fail("Expected RuntimeException for no current user.");
        } catch (RuntimeException e) {
            assertEquals("Please sign in first!", e.getMessage());
            verify(mockPresenter).prepareFailView("Please sign in first!");
        }
    }

    @Test
    public void testCreatePost_EmptyCategory() {
        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", "This is a post content", "path/to/attachment", "jpg", 0, 0,
                "Post Title", moderators, comments, ""
        );

        try {
            createPostInteractor.createPost(inputData);
            fail("Expected PostCreationFailedException for empty category.");
        } catch (PostCreationFailedException e) {
            assertEquals("Please choose category!", e.getMessage());
            verify(mockPresenter).prepareFailView("Please choose category!");
        }
    }

    @Test
    public void testCreatePost_NullContent() {
        CreatePostInputData inputData = new CreatePostInputData(
                "author@example.com", null, "path/to/attachment", "jpg", 0, 0,
                "Post Title", moderators, comments, "General"
        );

        try {
            createPostInteractor.createPost(inputData);
            fail("Expected PostCreationFailedException for null content.");
        } catch (PostCreationFailedException e) {
            assertEquals("Please fill in post contents!", e.getMessage());
            verify(mockPresenter).prepareFailView("Please fill in post contents!");
        }
    }

}



