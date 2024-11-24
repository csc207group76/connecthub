package com.CSC207.connecthub;

import daos.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import use_case.signup.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class signupTests {

    @Test
    public void successTest() {
        SignupInputData inputData = new SignupInputData("izabelle", "abc", "1234","1234", "izabelle@gmail.com", "12/12/12", "Izabelle marianne", new ArrayList<>(), new ArrayList<>());
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
        interactor.SignupUser(inputData);
    }
}

