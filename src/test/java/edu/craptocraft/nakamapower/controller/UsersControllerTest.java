package edu.craptocraft.nakamapower.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.craptocraft.nakamapower.entity.Countries;
import edu.craptocraft.nakamapower.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    private UsersController controller;
    
    private static Users entity = null;

    @Test
    public void test_create() {

        entity = new Users("test@example.com", "test", "password", new Countries("ES"));
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Users createdEntity = (Users) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getEmail(), createdEntity.getEmail());
        assertEquals(entity.getUser(), createdEntity.getUser());
        assertEquals(entity.getPassword(), createdEntity.getPassword());
        assertEquals(entity.getLastLog(), createdEntity.getLastLog());
        assertEquals(entity.getSession(), createdEntity.getSession());
        assertEquals(entity.getIdCountry(), createdEntity.getIdCountry());

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

        entity = new Users("test@example.com", "test", "password", new Countries("ES"));

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Users retrievedEntity = (Users) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getEmail(), retrievedEntity.getEmail());
        assertEquals(entity.getUser(), retrievedEntity.getUser());
        assertEquals(entity.getPassword(), retrievedEntity.getPassword());
        assertEquals(entity.getLastLog(), retrievedEntity.getLastLog());
        assertEquals(entity.getSession(), retrievedEntity.getSession());
        assertEquals(entity.getIdCountry(), retrievedEntity.getIdCountry());

        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Users("test@example.com", "test", "password", new Countries("ES"));

        ResponseEntity<?> createdResponse = controller.create(entity);

        Users createdEntity = (Users) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setEmail("test2@gmail.com");
        entity.setUser("test2");
        entity.setPassword("password2");
        entity.setLastLog(LocalDate.of(2023, 05, 15));
        
        Random random = new Random();
        Integer session = random.nextInt(1000000000);  // Generates a random number between 0 and 1000000000
        entity.setSession(session);
        assertNotEquals(0, entity.getSession(), 0);

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.CREATED, updatedResponse.getStatusCode());

        Users updatedEntity = (Users) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals("test2@gmail.com", updatedEntity.getEmail());
        assertEquals("test2", updatedEntity.getUser());
        assertEquals("password2", updatedEntity.getPassword());
        assertEquals(LocalDate.of(2023, 05, 15), updatedEntity.getLastLog());
        assertEquals(entity.getSession(), updatedEntity.getSession());
        assertEquals(entity.getIdCountry(), updatedEntity.getIdCountry());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Users("test@example.com", "test", "password", new Countries("ES"));

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }

}
