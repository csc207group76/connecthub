package app;

import controller.ViewManagerModel;
import controller.createComment.CreateCommentViewModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostPresenter;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import view.PostView;

public class GetPostUseCaseFactory {
    public GetPostUseCaseFactory() {

    }

    public static PostView create(ViewManagerModel viewManagerModel, PostViewModel postViewModel,
                                  HomepageViewModel homepageViewModel, DBPostDataAccessObject postDAO,
                                  CreateCommentViewModel createCommentViewModel) {
        final PostController postController = createGetPostUseCase(viewManagerModel, postViewModel, postDAO,
                createCommentViewModel);
        final HomepageController homepageController = HomepageUseCaseFactory.createHomepageController(viewManagerModel,
                homepageViewModel, postViewModel, postDAO);
        return new PostView(postController, postViewModel, homepageViewModel, homepageController);
    }

    public static PostController createGetPostUseCase(
        ViewManagerModel viewManagerModel, PostViewModel postViewModel, 
        DBPostDataAccessObject postDAO, CreateCommentViewModel createCommentViewModel
    ) {
        // TODO add home page view model
        final GetPostOutputBoundary getPostOutputBoundary = new PostPresenter(viewManagerModel, postViewModel,
                createCommentViewModel);
        
        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, getPostOutputBoundary);
        
        return new PostController(getPostInteractor);
    }
}
