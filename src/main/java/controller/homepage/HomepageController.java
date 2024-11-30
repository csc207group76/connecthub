package controller.homepage;

import entity.User;
import use_case.get_user.GetUserInteractor;
import use_case.getpost.GetPostInputBoundary;

public class HomepageController {
    private final GetPostInputBoundary getPostInteractor;
    private GetUserInteractor getUserInteractor;

    public HomepageController(GetPostInputBoundary getPostInteractor, GetUserInteractor getUserInteractor) {

        this.getPostInteractor = getPostInteractor;
        this.getUserInteractor = getUserInteractor;
    }

    public void fetchAllPosts() {
        this.getPostInteractor.getAllPosts();
    }

    public void switchToLoginView() {
        this.getPostInteractor.switchToPostView();
    }

    public void switchToHomePageView() {
        this.getPostInteractor.switchToHomePageView();
    }

    public User fetchUser() {
        return this.getUserInteractor.getCurrentUser();
    }


}
