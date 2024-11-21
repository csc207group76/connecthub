package use_case.createPost;

import entity.*;

import java.time.LocalDateTime;
import java.util.Date;

public class CreatePostInteractor implements CreatePostInputBoundary {
    private final CreatePostDataAccessInterface dataAccess;
    private final CreatePostOutputBoundary userPresenter;

    public CreatePostInteractor(CreatePostDataAccessInterface dataAccess, CreatePostOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.userPresenter = outputBoundary;
    }

    @Override
    public void createPost(CreatePostInputData inputData) {
        // TODO: more logic and database?
        // shoudl i add null here
        if (inputData.getPostTitle().isEmpty()) {
            userPresenter.prepareFailView("Please add title!");
        } else if (inputData.getCategory() == null) {
            userPresenter.prepareFailView("Please choose category!");
        } else if (dataAccess.existsByTitle(inputData.getPostTitle())) {
            throw new IllegalArgumentException("Post with the same title already exists.");
        } else {
            final Post post = new Post(inputData.getEntryID(), inputData.getAuthor(), inputData.getContent(), inputData.getTimestamp(),
                    inputData.getLastModified(), inputData.getLikes(), inputData.getDislikes(), inputData.getPostTitle(),
                    inputData.getComments(), inputData.getCategory());


            dataAccess.create(post);


            final CreatePostOutputData outputData = new CreatePostOutputData(inputData.getEntryID(), inputData.getAuthor(), inputData.getContent(), inputData.getTimestamp(), inputData.getDislikes(), inputData.getLikes(), inputData.getPostTitle(), inputData.getModerators(), inputData.getComments(), inputData.getCategory(), true);
            userPresenter.prepareSuccessView(outputData);
        }
    }
}