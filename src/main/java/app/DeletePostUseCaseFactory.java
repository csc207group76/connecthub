package app;

import controller.ViewManagerModel;
import controller.delete_post.DeletePostController;
import controller.post.PostPresenter;
import controller.post.PostViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import use_case.delete_post.DeletePostDataAccessInterface;
import use_case.delete_post.DeletePostInputBoundary;
import use_case.delete_post.DeletePostInteractor;
import use_case.delete_post.DeletePostOutputBoundary;

/**
 * This class contains the static factory function for creating the DeletePostController.
 */
public final class DeletePostUseCaseFactory {

    private DeletePostUseCaseFactory() {

    }

    /**
     * Factory function for creating the DeletePostController.
     *
     * @param postDAO the post data access object
     * @return the DeletePostController created for the provided input classes
     */
    public static DeletePostController create(
            DBPostDataAccessObject postDAO, DBUserDataAccessObject userDAO,
            ViewManagerModel viewManagerModel, PostViewModel postViewModel) {

        final DeletePostOutputBoundary postPresenter = new PostPresenter(viewManagerModel, postViewModel);
        final DeletePostInputBoundary deletePostInteractor =
                new DeletePostInteractor(postDAO, postPresenter, userDAO);

        return new DeletePostController(deletePostInteractor);
    }

    private static DeletePostController createDeletePostUseCase(DeletePostDataAccessInterface postDataAccessObject,
                                                                DBUserDataAccessObject userRepo) {

        final DeletePostInputBoundary deletePostInteractor = new DeletePostInteractor(
                postDataAccessObject, null, userRepo);

        return new DeletePostController(deletePostInteractor);
    }
}
