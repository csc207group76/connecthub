package app;

import controller.ViewManagerModel;
import controller.createComment.CreateCommentController;
import controller.createComment.CreateCommentPresenter;
import controller.createComment.CreateCommentViewModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.CommentFactory;
import use_case.createComment.CreateCommentDataAccessInterface;
import use_case.createComment.CreateCommentInputBoundary;
import use_case.createComment.CreateCommentInteractor;
import use_case.createComment.CreateCommentOutputBoundary;

import view.CreateCommentView;


public class CreateCommentUseCaseFactory {
    public CreateCommentUseCaseFactory(){

    }

    public static CreateCommentView create(ViewManagerModel viewManagerModel,
                                           PostViewModel postViewModel,
                                           HomepageViewModel homepageViewModel,
                                           DBPostDataAccessObject postDataAccessObject,
                                           CreateCommentViewModel viewModel,
                                           DBUserDataAccessObject commentDB,
                                           CreateCommentDataAccessInterface createCommentDataAccessInterface) {
        final CreateCommentController createCommentController = createCreateCommentUseCase(viewManagerModel,
                viewModel,
                commentDB,
                createCommentDataAccessInterface);

        final PostController postController = GetPostUseCaseFactory.createGetPostUseCase(viewManagerModel,
                postViewModel,
                postDataAccessObject,
                viewModel);

        final HomepageController homepageController = HomepageUseCaseFactory.createHomepageController(viewManagerModel,
                homepageViewModel,
                postViewModel,
                postDataAccessObject);

        return new CreateCommentView(createCommentController,
                viewModel,
                homepageViewModel,
                homepageController,
                postViewModel,
                postController);
    }
    private static CreateCommentController createCreateCommentUseCase(ViewManagerModel viewManagerModel,
                                                                      CreateCommentViewModel viewModel,
                                                                      DBUserDataAccessObject commentDB,
                                                                      CreateCommentDataAccessInterface createCommentDataAccessInterface) {
        final CreateCommentOutputBoundary outputBoundary = new CreateCommentPresenter(viewModel,viewManagerModel);
        final CreateCommentInputBoundary createCommentInteractor = new CreateCommentInteractor(createCommentDataAccessInterface,
                outputBoundary,
                commentDB,
                new CommentFactory());
        return new CreateCommentController(createCommentInteractor);

    }
}
