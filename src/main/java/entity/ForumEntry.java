package entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a forum entry. This is an abstract class.
 */
public abstract class ForumEntry {
    private String entryID;
    private String author;
    private Content content;
    private LocalDateTime postedDate;
    private LocalDateTime lastModifiedDate; 
    private int likes;
    private int dislikes;

    /**
     * Stores information of a forum entry.
     * @param entryID - an unique id to identify an entry.
     * @param author - the id of the user who posted the entry.
     * @param content - the content of the entry.
     * @param postedDate - the date the entry was posted.
     * @param lastModifiedDate - the date the entry was last modified.
     * @param likes - the number of likes in this entry.
     * @param dislikes - the number of dislikes in this entry.
     */
    public ForumEntry(String entryID, String author, Content content, LocalDateTime postedDate,
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Check for reference equality
        if (obj == null || getClass() != obj.getClass()) return false;  // Null or different class
        ForumEntry that = (ForumEntry) obj;
        return likes == that.likes &&
                dislikes == that.dislikes &&
                Objects.equals(entryID, that.entryID) &&
                Objects.equals(author, that.author) &&
                Objects.equals(content, that.content) &&
                Objects.equals(postedDate, that.postedDate) &&
                Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryID, author, content, postedDate, lastModifiedDate, likes, dislikes);
    }
}
