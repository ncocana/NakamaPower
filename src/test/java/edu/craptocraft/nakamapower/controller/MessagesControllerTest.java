package edu.craptocraft.nakamapower.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.craptocraft.nakamapower.entity.Messages;
import edu.craptocraft.nakamapower.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagesControllerTest {

    @Autowired
    private MessagesController controller;
    
    private static Messages entity = null;

    @Test
    public void test_create() {

        entity = new Messages(new Users(1), new Users(2), "Test");
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Messages createdEntity = (Messages) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getSender(), createdEntity.getSender());
        assertEquals(entity.getReceptor(), createdEntity.getReceptor());
        assertEquals(entity.getText(), createdEntity.getText());

        ResponseEntity<?> responseDelete = controller.delete(createdEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_getAll() {
        ResponseEntity<?> response = controller.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<?> entities = (List<?>) response.getBody();
        assertNotNull(entities);

    }

    @Test
    public void test_getOne() {

        entity = new Messages(new Users(1), new Users(2), "Test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Messages retrievedEntity = (Messages) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getSender(), retrievedEntity.getSender());
        assertEquals(entity.getReceptor(), retrievedEntity.getReceptor());
        assertEquals(entity.getText(), retrievedEntity.getText());

        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Messages(new Users(1), new Users(2), "Test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        Messages createdEntity = (Messages) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setText("Test 2");

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());

        Messages updatedEntity = (Messages) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals(createdEntity.getSender(), updatedEntity.getSender());
        assertEquals(createdEntity.getReceptor(), updatedEntity.getReceptor());
        assertEquals("Test 2", updatedEntity.getText());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Messages(new Users(1), new Users(2), "Test");

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }

}
