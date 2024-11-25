package use_case.login;

import daos.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class loginInteractorTest {

    @Test
    public void loginSuccessTest(){
        LoginInputData inputData = new LoginInputData("sam@gmail.com", "sam123");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Sam", "sam123", "Sam456", "11/11/11",
                "Sam Sam", "sam@gmail.com", new ArrayList<>(), new ArrayList<>());
        inMemoryUserDataAccessObject.save(user);

        LoginOutputBoundary loginSuccessPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                assertEquals("sam@gmail.com", loginOutputData.getUserEmail());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is failed");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(inMemoryUserDataAccessObject,
                loginSuccessPresenter, userFactory);
        interactor.LoginUser(inputData);
    }

    @Test
    public void wrongPasswordTest(){
        LoginInputData inputData = new LoginInputData("sam@gmail.com", "0000");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Sam", "sam123", "Sam456", "11/11/11",
                "Sam Sam", "sam@gmail.com", new ArrayList<>(), new ArrayList<>());
        inMemoryUserDataAccessObject.save(user);

        LoginOutputBoundary loginFailedPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case is failed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Incorrect password for \"sam@gmail.com\".",errorMessage);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(inMemoryUserDataAccessObject, loginFailedPresenter,
                userFactory);
        try {
            interactor.LoginUser(inputData);
            fail("Expected IncorrectPasswordException was not thrown.");
        }catch (IncorrectPasswordException e){
            assertEquals("Incorrect password for \"sam@gmail.com\".", e.getMessage());
        }
        // Remember to commit the loginInteractor file after changing it
    }

    @Test
    public void wrongEmailTest(){
        LoginInputData inputData = new LoginInputData("david@gmail.com", "0000");
        InMemoryUserDataAccessObject inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Sam", "sam123", "Sam456", "11/11/11",
                "Sam Sam", "sam@gmail.com", new ArrayList<>(), new ArrayList<>());
        inMemoryUserDataAccessObject.save(user);

        LoginOutputBoundary loginFailedPresenter = new LoginOutputBoundary() {
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case is failed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("david@gmail.com: Account does not exist.",errorMessage);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(inMemoryUserDataAccessObject, loginFailedPresenter,
                userFactory);
        try {
            interactor.LoginUser(inputData);
            fail("Expected AccountDoesNotExistException was not thrown.");
        }catch (AccountDoesNotExistException e){
            assertEquals("david@gmail.com: Account does not exist.", e.getMessage());
        }
    }

}
