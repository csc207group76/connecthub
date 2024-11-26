package use_case.login;

import entity.CommonUserFactory;
import entity.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class loginInteractorTest {

    private LoginDataAccessInterface mockDataAccess;
    private LoginOutputBoundary mockPresenter;
    private LoginInteractor interactor;
    private JSONObject jsonUser;

    @Before
    public void setUp() throws JSONException {
        this.mockPresenter = Mockito.mock(LoginOutputBoundary.class);
        this.mockDataAccess = Mockito.mock(LoginDataAccessInterface.class);
        this.interactor = new LoginInteractor(mockDataAccess, mockPresenter, new CommonUserFactory());

        User testUser = new CommonUserFactory().create(
                "Sam", "sam123", "Sam456", "11/11/11",
                "Sam Sam", "sam@gmail.com", new ArrayList<>(), new ArrayList<>());
        this.jsonUser = getJsonObject(testUser);
    }

    @Test
    public void loginSuccessTest(){

        when(mockDataAccess.existsByEmail("sam@gmail.com")).thenReturn(true);
        when(mockDataAccess.getUserByEmail("sam@gmail.com")).thenReturn(jsonUser);

        LoginInputData inputData = new LoginInputData("sam@gmail.com", "sam123");
        interactor.LoginUser(inputData);

        ArgumentCaptor<LoginOutputData> captor = ArgumentCaptor.forClass(LoginOutputData.class);
        verify(mockPresenter).prepareSuccessView(captor.capture());

        LoginOutputData capturedData = captor.getValue();
        assertEquals("sam@gmail.com", capturedData.getUserEmail());
        assertEquals("sam123", capturedData.getPassword());

        // Ensure no failure methods were called
        verify(mockPresenter, never()).prepareFailView(anyString());
    }

    @Test
    public void wrongPasswordTest() throws JSONException {

        when(mockDataAccess.getUserByEmail("sam@gmail.com")).thenReturn(jsonUser);
        when(mockDataAccess.existsByEmail("sam@gmail.com")).thenReturn(true);

        LoginInputData inputData = new LoginInputData("sam@gmail.com", "WrongPassword");
        try {
            interactor.LoginUser(inputData);
            fail("Expected IncorrectPasswordException was not thrown.");
        } catch (IncorrectPasswordException e) {
            assertEquals("Incorrect password for \"sam@gmail.com\".", e.getMessage());
        }

        verify(mockPresenter, never()).prepareSuccessView(any());
        verify(mockPresenter).prepareFailView("Incorrect password for \"sam@gmail.com\".");
    }

    @Test
    public void wrongEmailTest() {

        when(mockDataAccess.existsByEmail("david@gmail.com")).thenReturn(false);
        LoginInputData inputData = new LoginInputData("david@gmail.com", "0000");

        try {
            interactor.LoginUser(inputData);
            fail("Expected AccountDoesNotExistException was not thrown.");
        } catch (AccountDoesNotExistException e) {
            assertEquals("david@gmail.com: Account does not exist.", e.getMessage());
        }

        verify(mockPresenter).prepareFailView("david@gmail.com: Account does not exist.");
        verify(mockPresenter, never()).prepareSuccessView(any(LoginOutputData.class));
    }

    private static JSONObject getJsonObject(User testUser) throws JSONException {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", testUser.getUsername());
        jsonUser.put("password", testUser.getPassword());
        jsonUser.put("userId", testUser.getUserID());
        jsonUser.put("birth_date", testUser.getBirthDate());
        jsonUser.put("full_name", testUser.getFullName());
        jsonUser.put("email", testUser.getEmail());

        JSONArray moderatingData = new JSONArray();
        moderatingData.put("1");
        moderatingData.put("2");
        moderatingData.put("3");
        jsonUser.put("moderating", moderatingData);

        JSONArray postsData = new JSONArray();
        postsData.put("1");
        postsData.put("2");
        postsData.put("3");
        jsonUser.put("posts", postsData);
        return jsonUser;
    }
}
