package app;

import controller.ViewManagerModel;
import controller.delete_post.DeletePostController;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.login.LoginViewModel;
import controller.logout.LogoutController;
import controller.post.PostController;
import controller.post.PostPresenter;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import view.PostView;
public class GetPostUseCaseFactory {
    public GetPostUseCaseFactory() {
    }

    public static PostView create(ViewManagerModel viewManagerModel, PostViewModel postViewModel,
                                  HomepageViewModel homepageViewModel,
                                  DBPostDataAccessObject postDAO,
                                  DBUserDataAccessObject userDAO) {
        final PostController postController = createGetPostUseCase(viewManagerModel, postViewModel, postDAO);
        final HomepageController homepageController = HomepageUseCaseFactory.createHomepageController(viewManagerModel,
                homepageViewModel, postViewModel, postDAO, userDAO);
        final DeletePostController deletePostController = DeletePostUseCaseFactory.create(postDAO, userDAO, viewManagerModel, postViewModel);
        PostPresenter postPresenter = new PostPresenter(viewManagerModel, postViewModel);

        return new PostView(postController, postViewModel, homepageViewModel, homepageController, deletePostController, userDAO, postPresenter);
    }

    public static PostController createGetPostUseCase(
            ViewManagerModel viewManagerModel, PostViewModel postViewModel,
            DBPostDataAccessObject postDAO
    ) {
        // TODO add home page view model
        final GetPostOutputBoundary getPostOutputBoundary = new PostPresenter(viewManagerModel, postViewModel);

        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, getPostOutputBoundary);

        return new PostController(getPostInteractor);
    }
}