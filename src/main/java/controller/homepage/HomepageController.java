package controller.homepage;

import use_case.getpost.GetPostInputBoundary;

public class HomepageController {
    private final GetPostInputBoundary getPostInteractor;

    public HomepageController(GetPostInputBoundary getPostInteractor) {
        this.getPostInteractor = getPostInteractor;
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
}
