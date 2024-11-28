package use_case.createPost;

import daos.InMemoryPostDataAccessObject;
import daos.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CreatePostTests {

    @Test
    public void successPostCreationTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject(); // For user signup
       ; // For post creation

        // creating a user
        User user = new CommonUserFactory().create("izabelle", "1234", "abc", "12/12/12", "Izabelle marianne", "izabelle@gmail.com", new ArrayList<>(), new ArrayList<>());
        userRepository.save(user);
        // check they have been saved
        assertTrue(userRepository.existsByUsername("izabelle"));
        // now create the post
        InMemoryPostDataAccessObject postRepository = new InMemoryPostDataAccessObject(userRepository);
        PostContent content = new PostContent("hello world!", "excel", "text");
        CreatePostInputData inputData = new CreatePostInputData("1234", user, content, LocalDateTime.now(), LocalDateTime.now(), 0, 0, "First post!", new ArrayList<>(), new ArrayList<>(), "sports");

//        // Input data for creating a post
//        Post post = new Post("1234", user , content , LocalDateTime.now(),
//                LocalDateTime.now(),0, 0, "First post!",
//                new ArrayList<>(), "sports");

        // Mock presenter
        CreatePostOutputBoundary successPresenter = new CreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePostOutputData outputData) {
                // Check output data is as expected
                assertEquals("1234", outputData.getEntryID());
                assertEquals(content.getBody(), outputData.getContent().getBody());
                assertEquals(user.getUserID(), outputData.getAuthor().getUserID());
                // Ensure the post is saved in the repository
                assertTrue(postRepository.existsByTitle("First post!"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        // Create the interactor and execute the use case
        CreatePostInputBoundary interactor = new CreatePostInteractor(postRepository, successPresenter);
        interactor.createPost(inputData);


//        assertTrue(postRepository.existsByTitle("First post!"));
//        Post createdPost = postRepository.getPostByTitle("First post!");
//        assertNotNull(createdPost);
//        assertEquals("hello world!", createdPost.getContent().getBody());
//        assertEquals("izabelle", createdPost.getAuthor().getName());
    }
}



