package app;

import controller.ViewManagerModel;
import controller.create_post.CreatePostController;
import controller.create_post.CreatePostViewModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
import controller.login.LoginViewModel;
import controller.logout.LogoutController;
import controller.logout.LogoutPresenter;
import controller.post.PostController;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.PostFactory;
import entity.UserFactory;
import use_case.get_user.GetUserInteractor;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
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
            DBPostDataAccessObject postDAO, DBUserDataAccessObject userRepo, LoginViewModel loginViewModel) {

        final PostFactory postFactory = new PostFactory();
        final CreatePostViewModel createPostViewModel = new CreatePostViewModel();
        final HomepageController homepageController = createHomepageController(viewManagerModel, homepageViewModel, postViewModel, postDAO, userRepo);
        final CreatePostController createPostController = CreatePostUseCaseFactory.createCreatePostUseCase( viewManagerModel, createPostViewModel, postDAO,  userRepo, postFactory);
        final PostController postController = GetPostUseCaseFactory.createGetPostUseCase(viewManagerModel, postViewModel, postDAO);
        final LogoutController logoutController = createLogoutController(viewManagerModel, loginViewModel, userRepo);
        // TODO: chnage the signature here
        return new HomePageView(homepageController, createPostController, createPostViewModel, postController, homepageViewModel, logoutController);
    }

    public static HomepageController createHomepageController(
            ViewManagerModel viewManagerModel,
            HomepageViewModel homepageViewModel,
            PostViewModel postViewModel,
            DBPostDataAccessObject postDAO,
            DBUserDataAccessObject userDAO
    ) {
        final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
        
        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);
        UserFactory userFactory = new CommonUserFactory();
        final GetUserInteractor getUserInteractor = new GetUserInteractor(userDAO, userFactory);
        
        return new HomepageController(getPostInteractor, getUserInteractor);
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
