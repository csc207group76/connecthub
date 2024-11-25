package use_case.getpost;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        assertThrows(IllegalArgumentException.class, () -> interactor.getPost(inputData));
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

}
