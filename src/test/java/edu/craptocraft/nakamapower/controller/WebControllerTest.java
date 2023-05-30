package edu.craptocraft.nakamapower.controller;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.craptocraft.nakamapower.entity.Countries;
import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Login;
import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.FriendshipsService;
import edu.craptocraft.nakamapower.service.MessagesService;
import edu.craptocraft.nakamapower.service.UsersService;

import static org.junit.Assert.*;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebControllerTest {

    @Autowired
    private CountriesController countriesController;

    @Autowired
    private UsersController usersController;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendshipsController friendshipsController;

    @Autowired
    private FriendshipsService friendshipsService;
    
    @Autowired
    private WebController webController;

    @Autowired
    private MessagesService messagesService;
    
    private Users user = null;
    private Friendships friendship = null;
    private Login login = null;
    // private Prescriptions prescription = null;

    @Test
    public void test_root() {
        RedirectView redirectView = webController.root();
        assertEquals("/login", redirectView.getUrl());
    }

    @Test
    public void test_loginWithCurrentUser() {
        user = new Users("test@example.com", "test", "password", new Countries("ES"));
        usersService.create(user);
        ResponseEntity<?> response = usersController.getOne(user.getId());
        Users createdEntity = (Users) response.getBody();
        login = new Login(createdEntity.getEmail(), createdEntity.getPassword());
        webController.loginProcess(login);
        
        Object result = webController.login();
        assertTrue(result instanceof RedirectView);
        assertEquals("/friendships", ((RedirectView) result).getUrl());

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = usersController.delete(createdEntity.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_loginWithoutCurrentUser() {
        Object result = webController.login();
        assertTrue(result instanceof ModelAndView);
        assertEquals("login", ((ModelAndView) result).getViewName());
    }

    // @Test
    // public void test_loginProcessWithInvalidCredentials() {
    //     Login login2 = new Login("test2@example.com", "password");

    //     boolean result = webController.loginProcess(login2);
    //     assertFalse(result);
    //     assertNull(webController.getCurrentUser());
    // }

    @Test
    public void test_signUpWithCurrentUser() {
        user = new Users("test@example.com", "test", "password", new Countries("ES"));
        usersService.create(user);
        ResponseEntity<?> response = usersController.getOne(user.getId());
        Users createdEntity = (Users) response.getBody();
        login = new Login(createdEntity.getEmail(), createdEntity.getPassword());
        webController.loginProcess(login);

        Object result = webController.signUp();
        assertTrue(result instanceof RedirectView);
        assertEquals("friendships", ((RedirectView) result).getUrl());

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = usersController.delete(createdEntity.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_signUpWithoutCurrentUser() {
        Object result = webController.signUp();
        assertTrue(result instanceof ModelAndView);
        assertEquals("sign-up", ((ModelAndView) result).getViewName());
    }

    @Test
    public void test_friendshipsMenuWithCurrentUser() {
        user = new Users("test@example.com", "test", "password", new Countries("ES"));
        usersService.create(user);
        ResponseEntity<?> response = usersController.getOne(user.getId());
        Users createdEntity = (Users) response.getBody();
        login = new Login(createdEntity.getEmail(), createdEntity.getPassword());
        webController.loginProcess(login);

        Object result = webController.friendshipsMenu();
        assertTrue(result instanceof ModelAndView);
        ModelAndView modelAndView = (ModelAndView) result;
        assertEquals("friendships", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("currentUser"));
        assertNotNull(modelAndView.getModel().get("friendships"));

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = usersController.delete(createdEntity.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_friendshipsMenuWithoutCurrentUser() {
        Object result = webController.friendshipsMenu();
        assertTrue(result instanceof RedirectView);
        assertEquals("/login", ((RedirectView) result).getUrl());
    }

    @Test
    public void test_friendshipsCreateWithCurrentUser() {
        user = new Users("test@example.com", "test", "password", new Countries("ES"));
        usersService.create(user);
        ResponseEntity<?> response = usersController.getOne(user.getId());
        Users createdEntity = (Users) response.getBody();
        login = new Login(createdEntity.getEmail(), createdEntity.getPassword());
        webController.loginProcess(login);

        Object result = webController.friendshipsCreate();
        assertTrue(result instanceof ModelAndView);
        ModelAndView modelAndView = (ModelAndView) result;
        assertEquals("friendships-create", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("currentUser"));
        assertNotNull(modelAndView.getModel().get("users"));
        assertNotNull(modelAndView.getModel().get("countries"));

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = usersController.delete(createdEntity.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_friendshipsCreateWithoutCurrentUser() {
        Object result = webController.friendshipsCreate();
        assertTrue(result instanceof RedirectView);
        assertEquals("/login", ((RedirectView) result).getUrl());
    }

    @Test
    public void test_friendshipsMenuDeleteWithCurrentUser() {
        user = new Users("test@example.com", "test", "password", new Countries("ES"));
        usersService.create(user);
        ResponseEntity<?> responseUsers = usersController.getOne(user.getId());
        Users createdUser = (Users) responseUsers.getBody();

        login = new Login(createdUser.getEmail(), createdUser.getPassword());
        webController.loginProcess(login);

        Object result = webController.friendshipsMenuDelete(createdUser.getId());
        assertTrue(result instanceof RedirectView);
        assertEquals("/friendships", ((RedirectView) result).getUrl());

        webController.logoutProcess();

        ResponseEntity<?> responseDeleteDoctor = usersController.delete(createdUser.getId());
        assertNotNull(responseDeleteDoctor);
        assertEquals(HttpStatus.OK, responseDeleteDoctor.getStatusCode());
    }

    @Test
    public void test_friendshipsMenuDeleteWithoutCurrentUser() {
        int id = 123;
        Object result = webController.friendshipsMenuDelete(id);
        assertTrue(result instanceof RedirectView);
        assertEquals("/login", ((RedirectView) result).getUrl());
    }

    @Test
    public void test_friendshipsChatWithCurrentUser() {
        user = new Users("test@example.com", "test", "password", new Countries("ES"));
        usersService.create(user);
        ResponseEntity<?> responseUsers = usersController.getOne(user.getId());
        Users createdUser = (Users) responseUsers.getBody();

        friendship = new Friendships(new Users(1), new Users(2));
        friendshipsService.create(friendship);
        ResponseEntity<?> responseFriendship = friendshipsController.getOne(friendship.getId());
        Friendships createdFriendship = (Friendships) responseFriendship.getBody();

        login = new Login(createdUser.getEmail(), createdUser.getPassword());
        webController.loginProcess(login);

        Object result = webController.friendshipsChat(createdFriendship.getId());
        ModelAndView modelAndView = (ModelAndView) result;
        assertEquals("chat", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("currentUser"));
        assertNotNull(modelAndView.getModel().get("users"));
        assertNotNull(modelAndView.getModel().get("messages"));
        assertNotNull(modelAndView.getModel().get("friendship"));

        webController.logoutProcess();

        ResponseEntity<?> responseDeleteFriendship = friendshipsController.delete(createdFriendship.getId());
        assertNotNull(responseDeleteFriendship);
        assertEquals(HttpStatus.OK, responseDeleteFriendship.getStatusCode());

        ResponseEntity<?> responseDeleteDoctor = usersController.delete(createdUser.getId());
        assertNotNull(responseDeleteDoctor);
        assertEquals(HttpStatus.OK, responseDeleteDoctor.getStatusCode());
    }

    @Test
    public void test_friendshipsChatWithoutCurrentUser() {
        int id = 123;
        Object result = webController.friendshipsChat(id);
        assertTrue(result instanceof RedirectView);
        assertEquals("/login", ((RedirectView) result).getUrl());
    }

}
