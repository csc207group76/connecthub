package use_case.logout;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogoutInteractorTest {
    @Test
    public void LogOutSuccessTest(){

        LogoutDataAccessInterface mockDataAccess = mock(LogoutDataAccessInterface.class);
        LogoutOutputBoundary mockPresenter = mock(LogoutOutputBoundary.class);
        doNothing().when(mockDataAccess).logoutUser();

        LogoutInputData logoutInputData = new LogoutInputData("sam111");
        LogoutInputBoundary interactor = new LogoutInteractor(mockDataAccess, mockPresenter);
        interactor.logoutUser(logoutInputData);
        interactor.switchToSignupView();

        ArgumentCaptor<LogoutOutputData> captor = ArgumentCaptor.forClass(LogoutOutputData.class);
        verify(mockPresenter).prepareSuccessView(captor.capture());

        LogoutOutputData captureData = captor.getValue();
        assertEquals("sam111", captureData.getUserID());
        assertTrue(captureData.isLogoutSuccessful());

        verify(mockPresenter, never()).prepareFailView(anyString());
    }
}

