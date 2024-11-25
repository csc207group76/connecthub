package use_case.create_post;

import entity.*;

import java.util.List;
import java.util.UUID;

import daos.DBUserDataAccessObject;

public class CreatePostInteractor implements CreatePostInputBoundary {
    private final CreatePostDataAccessInterface dataAccess;
    private final DBUserDataAccessObject userRepo;
    private final CreatePostOutputBoundary userPresenter;
    private final PostFactory postFactory;

    public CreatePostInteractor(CreatePostDataAccessInterface dataAccess, 
                                DBUserDataAccessObject userRepo,
                                CreatePostOutputBoundary outputBoundary, 
                                PostFactory postFactory) {
        this.dataAccess = dataAccess;
        this.userRepo = userRepo;
        this.userPresenter = outputBoundary;
        this.postFactory = postFactory;
    }

    @Override
    public void createPost(CreatePostInputData inputData) {
        User currentUser = this.userRepo.getCurrentUser();
        if (currentUser == null) {
            userPresenter.prepareFailView("Please sign in first!");
            throw new RuntimeException("Please sign in first!");
        }

        if (inputData.getPostTitle().isEmpty()) {
            userPresenter.prepareFailView("Please add title!");
            throw new PostCreationFailedException("Please add title!");
        } else if (inputData.getCategory() == null || inputData.getCategory().equals("")) {
            userPresenter.prepareFailView("Please choose category!");
            throw new PostCreationFailedException("Please choose category!");
        } else if (inputData.getPostCotent() == null || inputData.getPostCotent().equals("")) {
            // post content is the body text of the post, not the entire content with attachment path and file type
            userPresenter.prepareFailView("Please fill in post contents!");
            throw new PostCreationFailedException("Please fill in post contents!");
        } else {
            final Post post = this.postFactory.createPost(
                UUID.randomUUID().toString(),
                currentUser,
                inputData.getPostCotent(),
                inputData.getAttachmentPath(),
                inputData.getFileType(),
                inputData.getPostTitle(),
                inputData.getCategory()
            );

            dataAccess.createPost(post);
            List<String> userPosts = currentUser.getPosts();
            userPosts.add(post.getEntryID()); // Aliases user's post list
            this.userRepo.updateUserPosts(currentUser);

            final CreatePostOutputData outputData = new CreatePostOutputData(
                post.getEntryID(),
                post.getAuthor(),
                post.getContent(),
                post.getPostedDate(),
                post.getLastModifiedDate(),
                post.getLikes(),
                post.getDislikes(),
                post.getPostTitle(),
                post.getComments(),
                post.getCategory(),
                true
            );
            userPresenter.prepareSuccessView(outputData);
        }
    }
}