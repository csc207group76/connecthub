package use_case.getpost;

import controller.createComment.CreateCommentViewModel;
import controller.post.PostState;
import entity.Comment;
import entity.Content;
import entity.Post;
import entity.PostContent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Get Post Interactor.
 */
public class GetPostInteractor implements GetPostInputBoundary {
    private final GetPostDataAccessInterface postDB;
    private final GetPostOutputBoundary getPostPresenter;

    public GetPostInteractor(GetPostDataAccessInterface postDB,
                             GetPostOutputBoundary getPostPresenter) {
        this.postDB = postDB;
        this.getPostPresenter = getPostPresenter;
    }

    @Override
    public Post getPost(GetPostInputData postInputData) throws IllegalArgumentException {
        final String entryID = postInputData.getEntryID();
        if (entryID == null) {
            throw new IllegalArgumentException();
        }
        try {
            final JSONObject retrievedPostJSON = postDB.getPostByEntryID(entryID);
            if (retrievedPostJSON == null) {
                getPostPresenter.prepareFailView("Post with entryID " + entryID + " not found.");
                throw new PostNotFoundException(entryID);
            }

            final Post retrievedPost = jsonToPost(retrievedPostJSON);
            final GetPostOutputData retrievedPostOutputData = new GetPostOutputData(
                retrievedPost.getEntryID(),
                retrievedPost.getPostTitle(),
                retrievedPost.getContent(),
                retrievedPost.getComments());
            getPostPresenter.prepareSuccessView(retrievedPostOutputData);
            return retrievedPost;
        }
        catch (Exception ex) {
            getPostPresenter.prepareFailView(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<JSONObject> postDatas = this.postDB.getAllPosts();
        List<Post> posts = new ArrayList<>();

        for (JSONObject postData : postDatas) {
            posts.add(this.jsonToPost(postData));
        }

        final GetPostOutputData retrievedPostOutputData = new GetPostOutputData(posts);
        getPostPresenter.prepareSuccessView(retrievedPostOutputData);
        return posts;
    }

    @Override
    public void switchToPostView() {
        getPostPresenter.switchToPostView();
    }

    @Override
    public void switchToHomePageView() {
        getPostPresenter.switchToHomePageView();
    }

    @Override
    public void switchToCreateCommentView(PostState poststate) { getPostPresenter.switchToCreateCommentView(poststate);}

    public void switchBack(CreateCommentViewModel createCommentViewModel){
        getPostPresenter.switchBack(createCommentViewModel);
    }

    private Post jsonToPost(JSONObject postData) {
        Content postContent = new PostContent(postData.getString("content_body"),
                "test",
                postData.getString("file_type"));

        JSONArray commentData = postData.getJSONArray("comments");
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentData.length(); i++){
            // TODO modify when implementing the comment feature. Will likely need to change the DAO implementation
            // Comment comment = new Comment();
            comments.add(null);
        }


        return new Post(
                postData.getString("post_id"),
                postData.getString("author"),
                postContent,
                LocalDateTime.parse(postData.getString("posted_date")),
                LocalDateTime.parse(postData.getString("last_modified")),
                postData.getInt("likes"),
                postData.getInt("dislikes"),
                postData.getString("title"),
                comments,
                postData.getString("category")
        );
    }
}
