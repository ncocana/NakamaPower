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

import edu.craptocraft.nakamapower.entity.Countries;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesControllerTest {

    @Autowired
    private CountriesController controller;
    
    private static Countries entity = null;

    @Test
    public void test_create() {

        entity = new Countries("TS", "Test");
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Countries createdEntity = (Countries) response.getBody();

        assertEquals(entity.getCode(), createdEntity.getCode());
        assertEquals(entity.getCountry(), createdEntity.getCountry());

        ResponseEntity<?> responseDelete = controller.delete(createdEntity.getCode());

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

        entity = new Countries("TS", "Test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getCode());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Countries retrievedEntity = (Countries) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getCode(), retrievedEntity.getCode());
        assertEquals(entity.getCountry(), retrievedEntity.getCountry());

        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getCode());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Countries("TS", "Test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        Countries createdEntity = (Countries) createdResponse.getBody();

        String entityId = createdEntity.getCode();

        assertEquals(entity.getCode(), entityId);

        entity.setCountry("Test2");

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.CREATED, updatedResponse.getStatusCode());

        Countries updatedEntity = (Countries) updatedResponse.getBody();

        assertEquals(createdEntity.getCode(), updatedEntity.getCode());
        assertEquals("Test2", updatedEntity.getCountry());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getCode());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Countries("TS", "Test");

        ResponseEntity<?> response = controller.delete(entity.getCode());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }

}
