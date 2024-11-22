package use_case.edit_post;

public interface EditPostOutputBoundary {

    // Method to prepare the view in case of a successful post edit
    void prepareSuccessView();

    // Method to prepare the view in case of a failed post edit
    void prepareFailView();
}