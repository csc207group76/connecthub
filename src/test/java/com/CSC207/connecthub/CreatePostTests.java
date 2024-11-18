package use_case;

import entity.*;
//import exception.PostNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.createPost.*;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatePostTests {

    private CreatePostDataAccessInterface mockPostDB;
    private CreatePostOutputBoundary mockPresenter;
    private CreatePostInteractor interactor;

    @BeforeEach
    void setUp() {
        mockPostDB = Mockito.mock(CreatePostDataAccessInterface.class);
        mockPresenter = Mockito.mock(CreatePostOutputBoundary.class);
    }

    @Test
    void GetPostSuccessTest() throws Exception {
        String entryID = "123";
        User author = new CommonUser("User1", "password","123", "12/2/2004", "User1 Name", "user@email.com");
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
    }

