package app;

import controller.ViewManagerModel;
import controller.create_post.CreatePostController;
import controller.create_post.CreatePostViewModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
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
import view.HomePageView;

public class HomepageUseCaseFactory {
    public HomepageUseCaseFactory() {

    }

    public static HomePageView create(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, PostViewModel postViewModel,
        DBPostDataAccessObject postDAO, DBUserDataAccessObject userRepo) {

        final PostFactory postFactory = new PostFactory();
        final CreatePostViewModel createPostViewModel = new CreatePostViewModel();
        final HomepageController homepageController = createHomepageController(viewManagerModel, homepageViewModel, postViewModel, postDAO, userRepo);
        final CreatePostController createPostController = CreatePostUseCaseFactory.createCreatePostUseCase( viewManagerModel, createPostViewModel, postDAO,  userRepo, postFactory);
        final PostController postController = GetPostUseCaseFactory.createGetPostUseCase(viewManagerModel, postViewModel, postDAO);
        // TODO: chnage the signature here
        return new HomePageView(homepageController, createPostController, createPostViewModel, postController, homepageViewModel);
    }

    public static HomepageController createHomepageController(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, PostViewModel postViewModel,
        DBPostDataAccessObject postDAO, DBUserDataAccessObject userRepo
    ) {
        final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
        
        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);
        UserFactory userFactory = new CommonUserFactory();
        final GetUserInteractor getUserInteractor = new GetUserInteractor(userRepo, userFactory);
        
        return new HomepageController(getPostInteractor, getUserInteractor);
    }
}
