package app;

import controller.ViewManagerModel;
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

    public static PostView create(
        ViewManagerModel viewManagerModel, PostViewModel postViewModel, 
        DBPostDataAccessObject postDAO
    ) {
        final PostController postController = createGetPostUseCase(viewManagerModel, postViewModel, postDAO);
        return new PostView(postController, postViewModel);
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