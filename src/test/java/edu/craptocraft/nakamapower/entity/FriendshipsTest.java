package edu.craptocraft.nakamapower.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FriendshipsTest {
    
    private Friendships friendship;
    private Users user1;
    private Users user2;
    
    @Before
    public void setUp() {
        // Create mock user objects for testing
        user1 = new Users("user1@example.com", "user1", "password", new Countries());
        user2 = new Users("user2@example.com", "user2", "password", new Countries());
        
        // Create a friendship object for testing
        friendship = new Friendships(user1, user2);
    }

    @Test
    public void testGetId() {
        assertEquals(0, friendship.getId());
    }

    @Test
    public void testSetId() {
        friendship.setId(1);
        assertEquals(1, friendship.getId());
    }

    @Test
    public void testGetIdUser() {
        assertNotNull(friendship.getIdUser());
        assertEquals("user1@example.com", friendship.getIdUser().getEmail());
    }

    @Test
    public void testSetIdUser() {
        Users newUser = new Users("newuser@example.com", "newuser", "password", new Countries());
        friendship.setIdUser(newUser);
        
        assertNotNull(friendship.getIdUser());
        assertEquals("newuser@example.com", friendship.getIdUser().getEmail());
    }

    @Test
    public void testGetIdFriend() {
        assertNotNull(friendship.getIdFriend());
        assertEquals("user2@example.com", friendship.getIdFriend().getEmail());
    }

    @Test
    public void testSetIdFriend() {
        Users newUser = new Users("newfriend@example.com", "newfriend", "password", new Countries());
        friendship.setIdFriend(newUser);
        
        assertNotNull(friendship.getIdFriend());
        assertEquals("newfriend@example.com", friendship.getIdFriend().getEmail());
    }

    @Test
    public void testEquals() {
        Friendships sameFriendship = new Friendships(user1, user2);
        Friendships differentFriendship = new Friendships(2);

        assertTrue(friendship.equals(sameFriendship));
        assertFalse(friendship.equals(differentFriendship));
    }

    @Test
    public void testHashCode() {
        Friendships sameFriendship = new Friendships(user1, user2);

        assertEquals(friendship.hashCode(), sameFriendship.hashCode());
    }
}
