package controller.login;

import controller.ViewManagerModel;
import controller.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {

    }

    @Override
    public void prepareSuccessView(LoginOutputData output) {}

    @Override
    public void prepareFailView(String s) {}

}
