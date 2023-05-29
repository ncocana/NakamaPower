package edu.craptocraft.nakamapower.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

public class UsersTest {
    
    private Users user;
    
    @Before
    public void setUp() {
        // Create a mock country object for testing
        Countries country = new Countries();
        country.setCode("TC");
        country.setCountry("Test Country");
        
        // Create a user object for testing
        user = new Users("test@example.com", "testuser", "password", country);
    }

    @Test
    public void testGetId() {
        assertEquals(0, user.getId());
    }

    @Test
    public void testSetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", user.getEmail());
    }

    @Test
    public void testGetUser() {
        assertEquals("testuser", user.getUser());
    }

    @Test
    public void testSetUser() {
        user.setUser("newuser");
        assertEquals("newuser", user.getUser());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testGetLastLog() {
        assertNull(user.getLastLog());
    }

    @Test
    public void testSetLastLog() {
        LocalDate date = LocalDate.now();
        user.setLastLog(date);
        assertEquals(date, user.getLastLog());
    }

    @Test
    public void testGetSession() {
        assertNull(user.getSession());
    }

    @Test
    public void testSetSession() {
        user.setSession(123456789);
        assertEquals(Integer.valueOf(123456789), user.getSession());
    }

    @Test
    public void testGetIdCountry() {
        assertNotNull(user.getIdCountry());
        assertEquals("Test Country", user.getIdCountry().getCountry());
    }

    @Test
    public void testSetIdCountry() {
        Countries newCountry = new Countries();
        newCountry.setCode("NC");
        newCountry.setCountry("New Country");
        
        user.setIdCountry(newCountry);
        
        assertNotNull(user.getIdCountry());
        assertEquals("New Country", user.getIdCountry().getCountry());
    }

    @Test
    public void testEquals() {
        Users sameUser = new Users("test@example.com", "testuser", "password", new Countries());
        Users differentUser = new Users(2);

        assertTrue(user.equals(sameUser));
        assertFalse(user.equals(differentUser));
    }

    @Test
    public void testHashCode() {
        Users sameUser = new Users("test@example.com", "testuser", "password", new Countries());

        assertEquals(user.hashCode(), sameUser.hashCode());
    }
}
