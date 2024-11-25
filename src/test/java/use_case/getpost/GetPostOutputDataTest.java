package use_case.getpost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import entity.Comment;
import entity.Content;
import entity.PostContent;

public class GetPostOutputDataTest {

    private GetPostOutputData getPostOutputData;
    private Content postContent;

    @BeforeEach
    public void setUp() {
        postContent = new PostContent("Body content", "/path/to/attachment", "pdf");
        getPostOutputData = new GetPostOutputData("entry123", "Test", postContent, new ArrayList<Comment>());
    }

    @Test
    public void testGetEntryID() {
        assertEquals("entry123", getPostOutputData.getEntryID(), "Entry ID should match.");
    }

    @Test
    public void testGetPostContent() {
        assertEquals(postContent, getPostOutputData.getPostContent(), "Post content should match.");
    }

    @Test
    public void testGetPostTitle() {
        assertEquals(postContent, getPostOutputData.getPostTitle(), "Post title should match.");
    }

    // Test hashCode()
    @Test
    public void testHashCode() {
        GetPostOutputData other = new GetPostOutputData("entry123", "Test", postContent, new ArrayList<Comment>());
        assertEquals(getPostOutputData.hashCode(), other.hashCode(), "Hash codes should be equal for same data.");
    }

    // Test for equals()
    @Test
    public void testEquals() {
        GetPostOutputData other = new GetPostOutputData("entry123", "Test", postContent, new ArrayList<Comment>());
        assertEquals(getPostOutputData, other, "GetPostOutputData should be equal if entryID and content the same.");
    }

    // Test for equals() with different entryID
    @Test
    public void testEqualsWithDifferentEntryID() {
        GetPostOutputData other = new GetPostOutputData("entry456", "Test", postContent, new ArrayList<Comment>());
        assertNotEquals(getPostOutputData, other, "GetPostOutputData should not equal if entryID is different.");
    }

    // Test for equals() with different content
    @Test
    public void testEqualsWithDifferentContent() {
        Content otherContent = new PostContent("Different body", "/new/path", "jpg");
        GetPostOutputData other = new GetPostOutputData("entry123", "Test", otherContent, new ArrayList<Comment>());
        assertNotEquals(getPostOutputData, other, "GetPostOutputData should not be equal if content is different.");
    }
}
