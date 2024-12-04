package use_case.signup;

import daos.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;

import static org.junit.Assert.*;


public class SignUpTests {
    // The print statements are just to debug

    @Test
    public void successTest() {
        SignupInputData inputData = new SignupInputData("izabelle",  "1234","1234", "izabelle@gmail.com", "12/12/12", "Izabelle marianne");
        SignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data and state
                assertEquals("izabelle", user.getUsername());
                assertTrue(userRepository.existsByUsername("izabelle"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // just to override
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.signupUser(inputData);

    }

    @Test
    public void userExistsExceptionTest() {
       
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                System.out.println("User created: " + user.getUsername());  
                assertEquals("izabelle", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                
                System.out.println("Error: " + error);  
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                
            }
        };

        
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());

        SignupInputData inputData1 = new SignupInputData("izabelle", "1234", "1234", "izabelle@gmail.com", "12/12/12", "Izabelle marianne");
        interactor.signupUser(inputData1); 

        assertTrue(userRepository.existsByEmail("izabelle@gmail.com")); 

        
        SignupInputData inputData2 = new SignupInputData("johndoe", "5678", "5678", "izabelle@gmail.com", "01/01/90", "John Doe");

        try {
            
            interactor.signupUser(inputData2);  
            fail("Expected UserExistsException to be thrown.");
        } catch (UserExistsException e) {
            
            System.out.println("Exception caught: " + e.getMessage());  
//            assertEquals("User already exists", e.getMessage());  
        }


    }

    @Test
    public void passwordMismatchTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Expected failure, but success view was triggered.");
            }

            @Override
            public void prepareFailView(String error) {                
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Expected failure, but switchToLoginView was called.");
            }
        };

       
        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());

        
        SignupInputData inputData = new SignupInputData("username", "password123", "password321", "email@example.com", "01/01/1990", "Full Name");

        
        interactor.signupUser(inputData);
    }

    @Test
    public void successfulSignupAndSwitchToLoginTest() {
        
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {                
                assertEquals("username", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Expected success, but failure view was triggered.");
            }

            @Override
            public void switchToLoginView() {             
                System.out.println("Switching to login view...");
                assertTrue(true); 
            }
        };

        
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());

       
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "email@example.com", "01/01/1990", "Full Name");

        
        interactor.signupUser(inputData);
    }

    @Test
    public void testSignupSuccessful() {
        
        SignupOutputData signupOutputData = new SignupOutputData("username", false);

        
        assertTrue(signupOutputData.useCaseSuccess());
    }

    @Test
    public void testSwitchToLoginView() {
        SignupOutputBoundary testPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // just to override
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view should not be triggered in this test.");
            }

            @Override
            public void switchToLoginView() {
                System.out.println("Switching to login view...");
                assertTrue(true); 
            }
        };

        SignupInteractor interactor = new SignupInteractor(
                new InMemoryUserDataAccessObject(),
                testPresenter,
                new CommonUserFactory()
        );

        interactor.switchToLoginView();
    }



}
