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

import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipsControllerTest {

    @Autowired
    private FriendshipsController controller;
    
    private static Friendships entity = null;

    @Test
    public void test_create() {

        entity = new Friendships(new Users(1), new Users(2));
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Friendships createdEntity = (Friendships) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getIdUser(), createdEntity.getIdUser());
        assertEquals(entity.getIdFriend(), createdEntity.getIdFriend());

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

        entity = new Friendships(new Users(1), new Users(2));

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Friendships retrievedEntity = (Friendships) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getIdUser(), retrievedEntity.getIdUser());
        assertEquals(entity.getIdFriend(), retrievedEntity.getIdFriend());

        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Friendships(new Users(1), new Users(2));

        ResponseEntity<?> createdResponse = controller.create(entity);

        Friendships createdEntity = (Friendships) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setIdUser(new Users(1));
        entity.setIdFriend(new Users(3));

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());

        Friendships updatedEntity = (Friendships) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals(createdEntity.getIdUser(), updatedEntity.getIdUser());
        assertEquals(createdEntity.getIdFriend(), updatedEntity.getIdFriend());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Friendships(new Users(1), new Users(2));

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }

}
