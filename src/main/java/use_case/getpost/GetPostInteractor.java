package use_case.getpost;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Comment;
import entity.Content;
import entity.Post;
import entity.PostContent;

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
            getPostPresenter.prepareFailView("Unable to retrieve post with entryID: " + entryID);
            return null;
        }

        try {
            final JSONObject retrievedPostJSON = postDB.getPostByEntryID(entryID);

            final Post retrievedPost = jsonToPost(retrievedPostJSON);

            final GetPostOutputData retrievedPostOutputData = new GetPostOutputData(
                    retrievedPost.getEntryID(),
                    retrievedPost.getPostTitle(),
                    retrievedPost.getContent(),
                    retrievedPost.getComments());
            getPostPresenter.prepareSuccessView(retrievedPostOutputData);
            return retrievedPost;
        }
        catch (PostNotFoundException ex) {
            getPostPresenter.prepareFailView(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Post> getAllPosts() {
        final List<JSONObject> postDatas = this.postDB.getAllPosts();
        final List<Post> posts = new ArrayList<>();

        for (JSONObject postData : postDatas) {
            posts.add(this.jsonToPost(postData));
        }

        final GetPostOutputData retrievedPostOutputData = new GetPostOutputData(posts);
        getPostPresenter.prepareSuccessView(retrievedPostOutputData);
        return posts;
    }

    @Override
    public List<Post> getPostsByCategory(String category) {
        final List<JSONObject> postDatas = this.postDB.getPostsByCategory(category);
        final List<Post> posts = new ArrayList<>();

        for (JSONObject postData : postDatas) {
            posts.add(this.jsonToPost(postData));
        }
        final GetPostOutputData retrievedFilteredPostOutputData = new GetPostOutputData(posts);
        getPostPresenter.prepareSuccessView(retrievedFilteredPostOutputData);
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

    private Post jsonToPost(JSONObject postData) {
        final Content postContent = new PostContent(postData.getString("content_body"),
                postData.getString("attachment_path"),
                postData.getString("file_type"));

        final JSONArray commentData = postData.getJSONArray("comments");
        final List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentData.length(); i++) {
            // TODO modify when implementing the comment feature. Will likely need to change the DAO implementation
            // Comment comment = new Comment();
            comments.add(null);
        }

        final Post post = new Post(
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
        return post;
    }
}
