package use_case.createPost;

import entity.Comment;
import entity.PostContent;
import entity.User;

import java.util.Date;

public class CreatePostOutputData {

    private final String entryID;
//    private final User author;
//    private final PostContent content;
//    private final Date timestamp;
//    private final int dislikes;
//    private final int likes;
//    private final String postTitle;
//    private final User[] moderators;
//    private final Comment[] comments;
//    private final String category;
    private final boolean creationSuccessful;

    public CreatePostOutputData(String entryID, boolean creationSuccessful) {
        this.entryID = entryID;
//        this.author = author;
//        this.content = content;
//        this.timestamp = timestamp;
//        this.dislikes = dislikes;
//        this.likes = likes;
//        this.postTitle = postTitle;
//        this.moderators = moderators;
//        this.comments = comments;
//        this.category = category;
        this.creationSuccessful = creationSuccessful;
    }


    public String getEntryID() {
        return entryID;
    }

    public boolean isCreationSuccessful() {
        return creationSuccessful;
    }
}