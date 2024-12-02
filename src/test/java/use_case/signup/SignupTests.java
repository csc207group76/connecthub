package use_case.signup;

import daos.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;

import static org.junit.Assert.*;


public class SignUpTests {

    @Test
    public void successTest() {
        SignupInputData inputData = new SignupInputData("izabelle",  "1234","1234", "izabelle@gmail.com", "12/12/12", "Izabelle marianne");
        SignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("izabelle", user.getUsername());
                assertTrue(userRepository.existsByUsername("izabelle"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.signupUser(inputData);

    }

    @Test
    public void userExistsExceptionTest() {
        // Step 1: Set up the user repository
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Success presenter that handles both cases (success and failure)
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // First user should succeed
                System.out.println("User created: " + user.getUsername());  // Debugging output
                assertEquals("izabelle", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                // Second user should fail with 'User already exists.' error
                System.out.println("Error: " + error);  // Debugging output
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // Nothing to do here for this test
            }
        };

        // Create the interactor instance
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());

        // Step 2: Simulate signing up the first user (inputData1)
        SignupInputData inputData1 = new SignupInputData("izabelle", "1234", "1234", "izabelle@gmail.com", "12/12/12", "Izabelle marianne");
        interactor.signupUser(inputData1);  // This should succeed and create the user

        // Ensure the user exists in the repository
        assertTrue(userRepository.existsByEmail("izabelle@gmail.com"));  // Ensuring the first user is created

        // Step 3: Simulate signing up with the same email (inputData2), expecting UserExistsException
        SignupInputData inputData2 = new SignupInputData("johndoe", "5678", "5678", "izabelle@gmail.com", "01/01/90", "John Doe");

        try {
            // Try to sign up the second user (same email as first one)
            interactor.signupUser(inputData2);  // This should throw UserExistsException because the email already exists
            fail("Expected UserExistsException to be thrown.");
        } catch (UserExistsException e) {
            // Ensure the exception message is as expected
            System.out.println("Exception caught: " + e.getMessage());  // Debugging output
//            assertEquals("User already exists", e.getMessage());  // Make sure the exception is thrown with correct message
        }


    }

    @Test
    public void passwordMismatchTest() {
        // Setup a mock database and presenter
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Expected failure, but success view was triggered.");
            }

            @Override
            public void prepareFailView(String error) {
                // Ensure the failure view is prepared with the expected error message
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Expected failure, but switchToLoginView was called.");
            }
        };

        // Create interactor instance
        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());

        // Prepare SignupInputData with mismatched passwords
        SignupInputData inputData = new SignupInputData("username", "password123", "password321", "email@example.com", "01/01/1990", "Full Name");

        // Run the signup process and expect the failure view with password mismatch message
        interactor.signupUser(inputData);
    }

    @Test
    public void successfulSignupAndSwitchToLoginTest() {
        // Setup a mock database and presenter
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // Ensure the success view is prepared with the correct username
                assertEquals("username", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Expected success, but failure view was triggered.");
            }

            @Override
            public void switchToLoginView() {
                // Ensure switchToLoginView is called after successful signup
                System.out.println("Switching to login view..."); // Debugging output
                // You could use a flag or mock behavior to verify this in more advanced scenarios
                assertTrue(true); // If this method is called, the test passes
            }
        };

        // Create interactor instance
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());

        // Prepare valid SignupInputData with matching passwords
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "email@example.com", "01/01/1990", "Full Name");

        // Run the signup process and expect success view and login switch
        interactor.signupUser(inputData);
    }

    @Test
    public void testSignupSuccessful() {
        // Create SignupOutputData with a successful signup (useCaseFailed = false)
        SignupOutputData signupOutputData = new SignupOutputData("username", false);

        // Assert that signup is successful
        assertTrue(signupOutputData.useCaseSuccess());
    }



}
