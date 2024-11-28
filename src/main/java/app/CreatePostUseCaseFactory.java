package app;

import controller.ViewManagerModel;
import controller.create_post.CreatePostController;
import controller.create_post.CreatePostPresenter;
import controller.create_post.CreatePostViewModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.PostFactory;
import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInteractor;
import use_case.create_post.CreatePostOutputBoundary;
import view.createPostView;

public class CreatePostUseCaseFactory {
    public CreatePostUseCaseFactory() {

    }

    public static createPostView create(ViewManagerModel viewManagerModel, PostViewModel postViewModel, CreatePostViewModel createPostViewModel,
                                        HomepageViewModel homepageViewModel, DBPostDataAccessObject postDAO, DBUserDataAccessObject userRepo,
                                        PostFactory postFactory) {

        final HomepageController homepageController = HomepageUseCaseFactory.createHomepageController(viewManagerModel,
                homepageViewModel, postViewModel, postDAO);
        final CreatePostController createPostController = createCreatePostUseCase(viewManagerModel,
                createPostViewModel, postDAO, userRepo, postFactory);
        return new createPostView(createPostViewModel,createPostController, homepageController);
    }

    public static CreatePostController createCreatePostUseCase(
            ViewManagerModel viewManagerModel,
            CreatePostViewModel createPostViewModel,
            DBPostDataAccessObject postDAO,
            DBUserDataAccessObject userRepo,
            PostFactory postFactory) {

        // Create the output boundary (presenter)
        final CreatePostOutputBoundary createPostOutputBoundary = new CreatePostPresenter(createPostViewModel, viewManagerModel);

        // Create the interactor
        final CreatePostInputBoundary createPostInteractor = new CreatePostInteractor(postDAO, userRepo, createPostOutputBoundary, postFactory);

        // Return the controller
        return new CreatePostController(createPostInteractor);
    }
}

