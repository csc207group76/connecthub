package app;

import controller.ViewManagerModel;
import controller.create_post.CreatePostController;
import controller.create_post.CreatePostPresenter;
import controller.create_post.CreatePostViewModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.PostFactory;
import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInteractor;
import use_case.create_post.CreatePostOutputBoundary;
import view.createPostView;

public class CreatePostUseCaseFactory {
    public CreatePostUseCaseFactory() {
        // Prevent instantiation
    }

    /**
     * Factory function for creating the CreatePostView.
     * @param viewManagerModel the ViewManagerModel for UI state management
     * @param createPostViewModel the CreatePostViewModel to inject into the CreatePostView
     * @param homepageViewModel the HomepageViewModel to inject into the HomepageController
     * @param postDAO the DBPostDataAccessObject to handle database operations
     * @return the CreatePostView with its dependencies injected
     */
    public static createPostView create(ViewManagerModel viewManagerModel,
                                        CreatePostViewModel createPostViewModel,
                                        HomepageViewModel homepageViewModel,
                                        DBPostDataAccessObject postDAO,
                                        DBUserDataAccessObject userRepo,
                                        PostFactory postFactory) {
        final CreatePostController createPostController = createCreatePostUseCase(viewManagerModel,
                createPostViewModel, postDAO, userRepo, postFactory);
        final HomepageController homepageController = HomepageUseCaseFactory.createHomepageController(viewManagerModel,
                homepageViewModel, createPostViewModel, postDAO);
        return new createPostView(createPostViewModel,createPostController, homepageController);
    }

    /**
     * Creates the CreatePostController and sets up the use case.
     * @param viewManagerModel the ViewManagerModel for UI state management
     * @param createPostViewModel the CreatePostViewModel to manage create post view state
     * @param postDAO the DBPostDataAccessObject to handle database operations
     * @return the configured CreatePostController
     */
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

