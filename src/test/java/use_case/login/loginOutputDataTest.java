package use_case.login;

import daos.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class loginOutputDataTest {

    // Test if the get password is working properly
    @Test
    public void getPasswordTest(){
        LoginOutputData loginOutputData = new LoginOutputData("sam@gmail.com", "sam123", true);
        assertEquals("sam123", loginOutputData.getPassword());
    }

    @Test
    public void isLoginSuccessfulTest(){
        LoginOutputData loginOutputData = new LoginOutputData("sam@gmail.com", "sam123", true);
        assertEquals(true, loginOutputData.isLoginSuccessful());
    }

    @Test
    public void setUserEmailTest(){
        LoginOutputData loginOutputData = new LoginOutputData("sam@gmail.com", "sam123", true);
        loginOutputData.setUserEmail("david@gmail.com");
        assertEquals("david@gmail.com", loginOutputData.getUserEmail());
    }

    @Test
    public void setPasswordTest(){
        LoginOutputData loginOutputData = new LoginOutputData("sam@gmail.com", "sam123", true);
        loginOutputData.setPassword("111");
        assertEquals("111", loginOutputData.getPassword());
    }
}
