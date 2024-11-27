package use_case.login;

import org.junit.Test;
import org.junit.Before;


import static org.junit.Assert.*;

public class LoginOutputDataTest {

    private LoginOutputData loginOutputData;

    @Before
    public void SetUp() {
        loginOutputData = new LoginOutputData("sam@gmail.com", "sam123", true);
    }

    @Test
    public void testGetPasswordTest(){
        assertEquals("sam123", loginOutputData.getPassword());
    }

    @Test
    public void testIsLoginSuccessfulTest(){
        assertEquals(true, loginOutputData.isLoginSuccessful());
    }

    @Test
    public void testSetUserEmailTest(){
        loginOutputData.setUserEmail("david@gmail.com");
        assertEquals("david@gmail.com", loginOutputData.getUserEmail());
    }

    @Test
    public void testSetPasswordTest(){
        loginOutputData.setPassword("111");
        assertEquals("111", loginOutputData.getPassword());
    }
}
