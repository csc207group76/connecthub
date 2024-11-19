package entity;

import java.time.LocalDateTime; 

/**
 * Represents a forum entry. This is an abstract class.
 */
public abstract class ForumEntry {
    private String entryID;
    private User author;
    private Content content;
    private LocalDateTime postedDate;
    private LocalDateTime lastModifiedDate; 
    private int likes;
    private int dislikes;

    /**
     * Stores information of a forum entry.
     * @param entryID - an unique id to identify an entry.
     * @param author - the user who posted the entry.
     * @param content - the content of the entry.
     * @param postedDate - the date the entry was posted.
     * @param lastModifiedDate - the date the entry was last modified.
     * @param likes - the number of likes in this entry.
     * @param dislikes - the number of dislikes in this entry.
     */
    public ForumEntry(String entryID, User author, Content content, LocalDateTime postedDate,
                      LocalDateTime lastModifiedDate, int likes, int dislikes) {
        this.entryID = entryID;
        this.author = author;
        this.content = content;
        this.postedDate = postedDate;
        this.lastModifiedDate = lastModifiedDate;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getEntryID() {
        return entryID;
    }

    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setEditDate(LocalDateTime getLastModifiedDate) {
        this.lastModifiedDate = getLastModifiedDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
