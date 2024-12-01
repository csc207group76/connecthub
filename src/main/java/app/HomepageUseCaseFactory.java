package app;

import controller.ViewManagerModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
import controller.login.LoginController;
import controller.login.LoginPresenter;
import controller.login.LoginViewModel;
import controller.logout.LogoutController;
import controller.logout.LogoutPresenter;
import controller.post.PostController;
import controller.post.PostViewModel;
import controller.signup.SignupViewModel;
import daos.DBPostDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import view.HomePageView;

public class HomepageUseCaseFactory {

    public HomepageUseCaseFactory() {

    }

    public static HomePageView create(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, PostViewModel postViewModel,
        DBPostDataAccessObject postDAO, LoginViewModel loginViewModel, LogoutDataAccessInterface logoutDataAccessInterface) {
        final HomepageController homepageController = createHomepageController(viewManagerModel, homepageViewModel, postViewModel, postDAO);
        final PostController postController = GetPostUseCaseFactory.createGetPostUseCase(viewManagerModel, postViewModel, postDAO);
        final LogoutController logoutController = createLogoutController(viewManagerModel, loginViewModel,logoutDataAccessInterface);
        return new HomePageView(homepageController, postController, homepageViewModel, logoutController);
    }

    public static HomepageController createHomepageController(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, PostViewModel postViewModel,
        DBPostDataAccessObject postDAO
    ) {
        final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
        
        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);
        
        return new HomepageController(getPostInteractor);
    }

    public static LogoutController createLogoutController(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LogoutDataAccessInterface userDataAccessObject) {

        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, loginViewModel);
        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        return new LogoutController(logoutInteractor);
    }
}
