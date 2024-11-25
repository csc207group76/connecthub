package use_case.create_post;

import entity.Comment;
import entity.User;

import java.util.List;


public class CreatePostInputData {

    private final String author;
    private final String postContent;
    private final String attachmentPath;
    private final String fileType;
    private final int dislikes;
    private final int likes;
    private final String postTitle;
    private final List<User> moderators;
    private final List<Comment> comments;
    private final String category;


    public CreatePostInputData(String author, String postContent, String attachmentPath, 
                               String fileType, int dislikes, int likes, String postTitle, 
                               List<User> moderators, List<Comment> comments, String category) {
        this.author = author;
        this.postContent = postContent;
        this.attachmentPath = attachmentPath;
        this.fileType = fileType;
        this.dislikes = dislikes;
        this.likes = likes;
        this.postTitle = postTitle;
        this.moderators = moderators;
        this.comments = comments;
        this.category = category;
    }


    public String getAuthor() {
        return author;
    }


    public String getPostContent() {
        return postContent;
    }


    public String getAttachmentPath() {
        return attachmentPath;
    }


    public String getFileType() {
        return fileType;
    }


    public int getDislikes() {
        return dislikes;
    }


    public int getLikes() {
        return likes;
    }


    public String getPostTitle() {
        return postTitle;
    }


    public List<User> getModerators() {
        return moderators;
    }


    public List<Comment> getComments() {
        return comments;
    }


    public String getCategory() {
        return category;
    }
}