package edu.craptocraft.nakamapower.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MessagesTest {
    
    private Messages message;
    private Users sender;
    private Users receptor;
    
    @Before
    public void setUp() {
        // Create mock user objects for testing
        sender = new Users("sender@example.com", "sender", "password", new Countries());
        receptor = new Users("receptor@example.com", "receptor", "password", new Countries());
        
        // Create a message object for testing
        message = new Messages(sender, receptor, "Hello, World!");
    }

    @Test
    public void testGetId() {
        assertEquals(0, message.getId());
    }

    @Test
    public void testSetId() {
        message.setId(1);
        assertEquals(1, message.getId());
    }

    @Test
    public void testGetSender() {
        assertNotNull(message.getSender());
        assertEquals("sender@example.com", message.getSender().getEmail());
    }

    @Test
    public void testSetSender() {
        Users newSender = new Users("newsender@example.com", "newsender", "password", new Countries());
        message.setSender(newSender);
        
        assertNotNull(message.getSender());
        assertEquals("newsender@example.com", message.getSender().getEmail());
    }

    @Test
    public void testGetReceptor() {
        assertNotNull(message.getReceptor());
        assertEquals("receptor@example.com", message.getReceptor().getEmail());
    }

    @Test
    public void testSetReceptor() {
        Users newReceptor = new Users("newreceptor@example.com", "newreceptor", "password", new Countries());
        message.setReceptor(newReceptor);
        
        assertNotNull(message.getReceptor());
        assertEquals("newreceptor@example.com", message.getReceptor().getEmail());
    }

    @Test
    public void testGetText() {
        assertEquals("Hello, World!", message.getText());
    }

    @Test
    public void testSetText() {
        message.setText("Goodbye, World!");
        assertEquals("Goodbye, World!", message.getText());
    }

    @Test
    public void testEquals() {
        Messages sameMessage = new Messages(sender, receptor, "Hello, World!");
        Messages differentMessage = new Messages(2);

        assertTrue(message.equals(sameMessage));
        assertFalse(message.equals(differentMessage));
    }

    @Test
    public void testHashCode() {
        Messages sameMessage = new Messages(sender, receptor, "Hello, World!");

        assertEquals(message.hashCode(), sameMessage.hashCode());
    }
}
