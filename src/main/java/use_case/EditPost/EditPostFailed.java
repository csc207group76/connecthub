package use_case.EditPost;

public class EditPostFailed extends Exception {

    // Constructor that accepts an error message
    public EditPostFailed(String error) {
        super(error);  // Call the superclass (Exception) constructor with the error message
    }
}