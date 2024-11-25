package controller.homepage;

import java.util.List;

import controller.ViewManagerModel;
import controller.post.PostViewModel;
import entity.Post;
import use_case.getpost.GetPostOutputBoundary;
import use_case.getpost.GetPostOutputData;

public class HomepagePresenter implements GetPostOutputBoundary {
    private final int PER_PAGE = 10;

    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;
    private final PostViewModel postViewModel;

    // TODO will need a home page view model
    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             PostViewModel postViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.postViewModel = postViewModel;
    }
    
    /**
     * Prepares the success view for the Get Post Use Case.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(GetPostOutputData outputData) {
        final HomepageState homepageState = this.homepageViewModel.getState();
        List<Post> posts = outputData.getAllPosts();

        homepageState.setPosts(posts.subList(0, Math.min(posts.size(), PER_PAGE)));

        homepageState.setPostsError(null);

        this.homepageViewModel.setState(homepageState);
        this.homepageViewModel.firePropertyChanged();

        this.viewManagerModel.setState(homepageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Get Post Use Case.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final HomepageState homepageState = this.homepageViewModel.getState();
        homepageState.setPostsError(null);
        this.homepageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPostView() {
        viewManagerModel.setState(postViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomePageView() {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
