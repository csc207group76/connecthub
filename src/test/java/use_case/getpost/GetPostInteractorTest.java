package use_case.getpost;

import entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;

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
    }

    @Test
    void GetPostSuccessTest() throws Exception {
        String entryID = "123";
        User author = new CommonUser("User One", "user1@example.com");
        Content content = new PostContent("This is a sample post content.", null, null);
        LocalDateTime postedDate = LocalDateTime.now();
        LocalDateTime lastModifiedDate = LocalDateTime.now();
        Post expectedPost = new Post(
                entryID,
                author,
                content,
                postedDate,
                lastModifiedDate,
                10,
                2,
                "Sample Post Title",
                Collections.emptyList(),
                "General"
        );

        when(mockPostDB.getPostByEntryID(entryID)).thenReturn(expectedPost);

        GetPostInputData inputData = new GetPostInputData(entryID);
        interactor = new GetPostInteractor(inputData, mockPostDB, mockPresenter);

        Post result = interactor.getPost(inputData);

        assertEquals(expectedPost, result);
        verify(mockPostDB).getPostByEntryID(entryID);
        // Check that presenter was called
        verify(mockPresenter).prepareSuccessView(new GetPostOutputData(entryID, content));
    }

    @Test
    void GetPostPostNotFoundTest() throws Exception {
        String entryID = "123";
        String expectedMessage = "Post with entryID " + entryID + " not found.";
        when(mockPostDB.getPostByEntryID(entryID)).thenThrow(new PostNotFoundException(entryID));

        GetPostInputData inputData = new GetPostInputData(entryID);
        interactor = new GetPostInteractor(inputData, mockPostDB, mockPresenter);

        Post result = interactor.getPost(inputData);

        assertNull(result);
        verify(mockPostDB).getPostByEntryID(entryID);
        verify(mockPresenter).prepareFailView(expectedMessage);
    }

    @Test
    void GetPostInvalidInputTest() {
        GetPostInputData inputData = new GetPostInputData(null);
        interactor = new GetPostInteractor(inputData, mockPostDB, mockPresenter);

        assertThrows(IllegalArgumentException.class, () -> interactor.getPost(inputData));
    }

    @Test
    void GetPostPresenterCalledOnSuccessTest() throws Exception {
        String entryID = "123";
        User author = new CommonUser("User One", "123");
        Content content = new PostContent("This is a sample post content.", null, null);
        LocalDateTime postedDate = LocalDateTime.now();
        LocalDateTime lastModifiedDate = LocalDateTime.now();
        Post expectedPost = new Post(
                entryID,
                author,
                content,
                postedDate,
                lastModifiedDate,
                10,
                2,
                "Sample Post Title",
                Collections.emptyList(),
                "General"
        );

        when(mockPostDB.getPostByEntryID(entryID)).thenReturn(expectedPost);

        GetPostInputData inputData = new GetPostInputData(entryID);
        interactor = new GetPostInteractor(inputData, mockPostDB, mockPresenter);

        GetPostOutputData outputData = new GetPostOutputData(entryID, content);

        interactor.getPost(inputData);

        verify(mockPresenter).prepareSuccessView(outputData);
    }
}