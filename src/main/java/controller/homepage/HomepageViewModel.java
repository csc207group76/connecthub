package controller.homepage;

import controller.ViewModel;

/**
 * The ViewModel for the HomePage view.
 */
public class HomepageViewModel extends ViewModel<HomepageState> {

    public static final String TITLE_LABEL = "Home Page";

    public HomepageViewModel() {
        super("home page");
        setState(new HomepageState());
    }
}
