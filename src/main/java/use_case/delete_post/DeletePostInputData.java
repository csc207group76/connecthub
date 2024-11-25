package use_case.delete_post;

/**
 Input Data for the Delete Post use-case
 */
public class DeletePostInputData {
    private String postId;
    private String userId;

    public DeletePostInputData(String PostId, String UserId) {
        this.postId = PostId;
        this.userId = UserId;
    }
    public String getPostId() {
        return postId;
    }
    public String getUserId() {
        return userId;
    }

}
