package use_case.getpost;

import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPostInteractorTest {

    private GetPostDataAccessInterface mockPostDB;
    private GetPostOutputBoundary mockPresenter;
    private GetPostInteractor interactor;

    @BeforeEach
    void setUp() {
        mockPostDB = Mockito.mock(GetPostDataAccessInterface.class);
        mockPresenter = Mockito.mock(GetPostOutputBoundary.class);
        interactor = new GetPostInteractor(mockPostDB, mockPresenter);
    }

    @Test
    void GetPostSuccessTest() throws Exception {
        String entryID = "123";
        String author = "Author1";
        String contentBody = "This is the content.";
        String attachmentPath = "path/to/attachment";
        String fileType = "text/plain";
        String title = "Sample Post";
        String category = "General";
        LocalDateTime postedDate = LocalDateTime.now().withNano(0);
        LocalDateTime lastModified = LocalDateTime.now().withNano(0);
        int likes = 5;
        int dislikes = 2;
        List<Comment> comments = new ArrayList<>();

        JSONObject postJSON = new JSONObject();
        postJSON.put("post_id", entryID);
        postJSON.put("author", author);
        postJSON.put("content_body", contentBody);
        postJSON.put("attachment_path", attachmentPath);
        postJSON.put("file_type", fileType);
        postJSON.put("title", title);
        postJSON.put("category", category);
        postJSON.put("posted_date", postedDate.toString());
        postJSON.put("last_modified", lastModified.toString());
        postJSON.put("likes", likes);
        postJSON.put("dislikes", dislikes);
        postJSON.put("comments", new JSONArray());

        when(mockPostDB.getPostByEntryID(entryID)).thenReturn(postJSON);

        GetPostInputData inputData = new GetPostInputData(entryID);

        Content expectedContent = new PostContent(contentBody, attachmentPath, fileType);
        Post expectedPost = new Post(
                entryID, author, expectedContent, postedDate, lastModified, likes, dislikes, title, Collections.emptyList(), category
        );

        GetPostOutputData expectedOutputData = new GetPostOutputData(entryID, title, expectedContent, comments);

        Post result = interactor.getPost(inputData);

        assertEquals(expectedPost, result, "Post objects don't match");

        verify(mockPostDB).getPostByEntryID(entryID);
        verify(mockPresenter).prepareSuccessView(expectedOutputData);
    }


    @Test
    void GetPostPostNotFoundTest() throws Exception {
        String entryID = "123";
        String expectedMessage = "Post with entryID " + entryID + " not found.";

        when(mockPostDB.getPostByEntryID(entryID)).thenThrow(new PostNotFoundException(entryID));

        GetPostInputData inputData = new GetPostInputData(entryID);

        Post result = interactor.getPost(inputData);

        assertNull(result);
        verify(mockPostDB).getPostByEntryID(entryID);
        verify(mockPresenter).prepareFailView(expectedMessage);
    }

    @Test
    void GetPostInvalidInputTest() {
        GetPostInputData inputData = new GetPostInputData(null);
        String expectedErrorMessage = "Unable to retrieve post with entryID: null";

        Post result = interactor.getPost(inputData);

        assertNull(result, "Result should be null when entryID is invalid.");
        verify(mockPresenter).prepareFailView(expectedErrorMessage);
    }

    @Test
    void GetPostPresenterCalledOnSuccessTest() throws Exception {
        String entryID = "123";
        String author = "Author1";
        String contentBody = "This is another test content.";
        String attachmentPath = "path/to/another/attachment";
        String fileType = "image/png";
        String title = "Another Post";
        String category = "Updates";
        LocalDateTime postedDate = LocalDateTime.now();
        LocalDateTime lastModified = LocalDateTime.now();
        int likes = 10;
        int dislikes = 1;
        List<Comment> comments = new ArrayList<>();

        // JSON response from mock database
        JSONObject postJSON = new JSONObject();
        postJSON.put("post_id", entryID);
        postJSON.put("author", author);
        postJSON.put("content_body", contentBody);
        postJSON.put("attachment_path", attachmentPath);
        postJSON.put("file_type", fileType);
        postJSON.put("title", title);
        postJSON.put("category", category);
        postJSON.put("posted_date", postedDate.toString());
        postJSON.put("last_modified", lastModified.toString());
        postJSON.put("likes", likes);
        postJSON.put("dislikes", dislikes);
        postJSON.put("comments", new JSONArray());

        when(mockPostDB.getPostByEntryID(entryID)).thenReturn(postJSON);

        GetPostInputData inputData = new GetPostInputData(entryID);
        Content expectedContent = new PostContent(contentBody, attachmentPath, fileType);
        GetPostOutputData expectedOutputData = new GetPostOutputData(entryID, title, expectedContent, comments);

        // Capture actual argument passed to presenter
        ArgumentCaptor<GetPostOutputData> captor = ArgumentCaptor.forClass(GetPostOutputData.class);

        // Call the interactor
        interactor.getPost(inputData);

        verify(mockPresenter).prepareSuccessView(captor.capture());
        assertEquals(expectedOutputData, captor.getValue());
    }


    @Test
    void GetAllPostsSuccessTest() throws Exception {
        // Prepare mock data
        String entryID1 = "123";
        String entryID2 = "456";
        String author = "Author1";
        String contentBody = "Content of post.";
        String attachmentPath = "path/to/attachment";
        String fileType = "text/plain";
        String title = "Post 1";
        String category = "General";
        LocalDateTime postedDate = LocalDateTime.now().withNano(0);
        LocalDateTime lastModified = LocalDateTime.now().withNano(0);
        int likes = 5;
        int dislikes = 2;
        List<Comment> comments = new ArrayList<>();

        // JSON responses from mock database
        JSONObject postJSON1 = new JSONObject();
        postJSON1.put("post_id", entryID1);
        postJSON1.put("author", author);
        postJSON1.put("content_body", contentBody);
        postJSON1.put("attachment_path", attachmentPath);
        postJSON1.put("file_type", fileType);
        postJSON1.put("title", title);
        postJSON1.put("category", category);
        postJSON1.put("posted_date", postedDate.toString());
        postJSON1.put("last_modified", lastModified.toString());
        postJSON1.put("likes", likes);
        postJSON1.put("dislikes", dislikes);
        postJSON1.put("comments", new JSONArray());

        JSONObject postJSON2 = new JSONObject(convertJsonToMap(postJSON1));
        postJSON2.put("post_id", entryID2); // Different ID for the second post

        List<JSONObject> postList = new ArrayList<>();
        postList.add(postJSON1);
        postList.add(postJSON2);

        when(mockPostDB.getAllPosts()).thenReturn(postList);

        // Define expected outputs
        Content expectedContent1 = new PostContent(contentBody, attachmentPath, fileType);
        Content expectedContent2 = new PostContent(contentBody, attachmentPath, fileType);

        Post expectedPost1 = new Post(entryID1, author, expectedContent1, postedDate, lastModified, likes, dislikes, title, comments, category);
        Post expectedPost2 = new Post(entryID2, author, expectedContent2, postedDate, lastModified, likes, dislikes, title, comments, category);

        List<Post> expectedPosts = new ArrayList<>();
        expectedPosts.add(expectedPost1);
        expectedPosts.add(expectedPost2);

        GetPostOutputData expectedOutputData = new GetPostOutputData(expectedPosts);

        // Call the interactor
        List<Post> result = interactor.getAllPosts();

        // Validate results
        assertEquals(expectedPosts, result);
        verify(mockPostDB).getAllPosts();
        verify(mockPresenter).prepareSuccessView(expectedOutputData);
    }

    @Test
    void GetPostsByCategorySuccessTest() throws Exception {
        String category = "General";
        String entryID1 = "123";
        String entryID2 = "456";
        String author = "Author1";
        String contentBody = "Content of post.";
        String attachmentPath = "path/to/attachment";
        String fileType = "text/plain";
        String title = "Post 1";
        LocalDateTime postedDate = LocalDateTime.now().withNano(0);
        LocalDateTime lastModified = LocalDateTime.now().withNano(0);
        int likes = 5;
        int dislikes = 2;
        List<Comment> comments = new ArrayList<>();

        // JSON responses from mock database
        JSONObject postJSON1 = new JSONObject();
        postJSON1.put("post_id", entryID1);
        postJSON1.put("author", author);
        postJSON1.put("content_body", contentBody);
        postJSON1.put("attachment_path", attachmentPath);
        postJSON1.put("file_type", fileType);
        postJSON1.put("title", title);
        postJSON1.put("category", category);
        postJSON1.put("posted_date", postedDate.toString());
        postJSON1.put("last_modified", lastModified.toString());
        postJSON1.put("likes", likes);
        postJSON1.put("dislikes", dislikes);
        postJSON1.put("comments", new JSONArray());

        JSONObject postJSON2 = new JSONObject(convertJsonToMap(postJSON1));
        postJSON2.put("post_id", entryID2); // Different ID for the second post

        List<JSONObject> postList = new ArrayList<>();
        postList.add(postJSON1);
        postList.add(postJSON2);

        when(mockPostDB.getPostsByCategory(category)).thenReturn(postList);

        // Define expected outputs
        Content expectedContent1 = new PostContent(contentBody, attachmentPath, fileType);
        Content expectedContent2 = new PostContent(contentBody, attachmentPath, fileType);

        Post expectedPost1 = new Post(entryID1, author, expectedContent1, postedDate, lastModified, likes, dislikes, title, comments, category);
        Post expectedPost2 = new Post(entryID2, author, expectedContent2, postedDate, lastModified, likes, dislikes, title, comments, category);

        List<Post> expectedPosts = new ArrayList<>();
        expectedPosts.add(expectedPost1);
        expectedPosts.add(expectedPost2);

        GetPostOutputData expectedOutputData = new GetPostOutputData(expectedPosts);

        // Call the interactor
        List<Post> result = interactor.getPostsByCategory(category);

        // Validate results
        assertEquals(expectedPosts, result);
        verify(mockPostDB).getPostsByCategory(category);
        verify(mockPresenter).prepareSuccessView(expectedOutputData);
    }

    @Test
    void SwitchToPostViewTest() {
        // Call the method
        interactor.switchToPostView();

        // Verify that the presenter was called with switchToPostView
        verify(mockPresenter).switchToPostView();
    }

    @Test
    void SwitchToHomePageViewTest() {
        // Call the method
        interactor.switchToHomePageView();

        // Verify that the presenter was called with switchToHomePageView
        verify(mockPresenter).switchToHomePageView();
    }

    // Utility method to convert JSONObject to Map
    private Map<String, Object> convertJsonToMap(JSONObject jsonObject) throws JSONException {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            map.put(key, jsonObject.get(key));
        }

        return map;
    }

}
