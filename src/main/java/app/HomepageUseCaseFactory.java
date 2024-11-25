package app;

import controller.ViewManagerModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import view.HomePageView;

public class HomepageUseCaseFactory {
    public HomepageUseCaseFactory() {

    }

    public static HomePageView create(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, PostViewModel postViewModel,
        DBPostDataAccessObject postDAO
    ) {
        final HomepageController homepageController = createHomepageController(viewManagerModel, homepageViewModel, postViewModel, postDAO);
        final PostController postController = GetPostUseCaseFactory.createGetPostUseCase(viewManagerModel, postViewModel, postDAO);
        return new HomePageView(homepageController, postController, homepageViewModel);
    }

    public static HomepageController createHomepageController(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, PostViewModel postViewModel,
        DBPostDataAccessObject postDAO
    ) {
        final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
        
        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);
        
        return new HomepageController(getPostInteractor);
    }
}
