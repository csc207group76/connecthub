package app;

import controller.delete_post.DeletePostController;
import daos.DBUserDataAccessObject;
import use_case.delete_post.DeletePostDataAccessInterface;
import use_case.delete_post.DeletePostInputBoundary;
import use_case.delete_post.DeletePostInteractor;

/**
 * This class contains the static factory function for creating the DeletePostController.
 */
public final class DeletePostUseCaseFactory {

    private DeletePostUseCaseFactory() {

    }

    /**
     * Factory function for creating the DeletePostController.
     *
     * @param postDataAccessObject the DeletePostDataAccessInterface to inject into the use case
     * @param userRepo the DBUserDataAccessObject to inject into the use case
     * @return the DeletePostController created for the provided input classes
     */
    public static DeletePostController create(DeletePostDataAccessInterface postDataAccessObject,
                                              DBUserDataAccessObject userRepo) {

        final DeletePostController deletePostController = createDeletePostUseCase(postDataAccessObject, userRepo);
        return deletePostController;
    }

    private static DeletePostController createDeletePostUseCase(DeletePostDataAccessInterface postDataAccessObject,
                                                                DBUserDataAccessObject userRepo) {

        final DeletePostInputBoundary deletePostInteractor = new DeletePostInteractor(
                postDataAccessObject, null, userRepo); // Pass `null` for the presenter as it's not used

        return new DeletePostController(deletePostInteractor);
    }
}
