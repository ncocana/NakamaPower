package edu.craptocraft.nakamapower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Login;
import edu.craptocraft.nakamapower.entity.Messages;
import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.CountriesService;
import edu.craptocraft.nakamapower.service.FriendshipsService;
import edu.craptocraft.nakamapower.service.LoginService;
import edu.craptocraft.nakamapower.service.MessagesService;
import edu.craptocraft.nakamapower.service.UsersService;

@RestController
public class WebController {

    @Autowired
    private LoginService serviceLogin;

    @Autowired
    private UsersService serviceUsers;
    
    @Autowired
    private CountriesService serviceCountries;
    
    @Autowired
    private FriendshipsService serviceFriendships;
    
    @Autowired
    private MessagesService serviceMessages;

    private Users currentUser = null;

    private static final String LOGIN = "login";
    private static final String SIGN_UP = "sign-up";
    private static final String FRIENDSHIPS = "friendships";
    private static final String FRIENDSHIPS_CREATE = "friendships-create";
    private static final String CHAT = "chat";

    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView("/" + LOGIN);
    }

    @GetMapping("/login")
    public Object login() {
        if (currentUser != null) {
            return new RedirectView("/" + FRIENDSHIPS);
        } else {
            return new ModelAndView(LOGIN);
        }
    }

    @PostMapping(path = "/login-process")
    public boolean loginProcess(@RequestBody Login request) {
        
        String email = request.getEmail();
        String password = request.getPassword();

        boolean doctorExists = serviceLogin.login(email, password);
        currentUser = serviceLogin.getUser();
        
        // If login is successful, return true.
        // If login fails, return false.
        return doctorExists;
    }

    @GetMapping(path = "/session/current-user")
    public Users getCurrentUser() {
        currentUser = serviceLogin.getUser();
        return currentUser;
    }

    @PostMapping(path = "/logout-process")
    public boolean logoutProcess() {
        
        serviceLogin.logOut();
        currentUser = null;
        
        // If log out is successful, return false.
        // If log out fails, return true.
        return serviceLogin.isLoggedIn();
    }

    @GetMapping("/sign-up")
    public Object signUp() {
        if (currentUser != null) {
            return new RedirectView(FRIENDSHIPS);
        } else {
            ModelAndView modelAndView = new ModelAndView(SIGN_UP);
            modelAndView.addObject("countries", serviceCountries.getAll());
            return modelAndView;
        }
    }

    @GetMapping("/friendships")
    public Object friendshipsMenu() {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView(FRIENDSHIPS);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("friendships", serviceFriendships.getAll());
            return modelAndView;
        } else {
            return new RedirectView("/" + LOGIN);
        }
    }

    @GetMapping("/friendships/create")
    public Object friendshipsCreate() {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(FRIENDSHIPS_CREATE);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("users", serviceUsers.getAll());
            modelAndView.addObject("countries", serviceCountries.getAll());
            return modelAndView;
        } else {
            return new RedirectView("/" + LOGIN);
        }
    }

    @GetMapping("/friendships/delete/{id}")
    public Object friendshipsMenuDelete(@PathVariable int id) {
        if (currentUser != null) {
            List<Messages> messages = serviceMessages.getAll();
            for (Messages message : messages) {
                if (message.getChat().getId() == id) {
                    serviceMessages.delete(message.getId());
                }
            }
            serviceFriendships.delete(id);
            return new RedirectView("/" + FRIENDSHIPS);
        } else {
            return new RedirectView("/" + LOGIN);
        }
    }

    @GetMapping("/friendships/chat/{id}")
    public Object friendshipsChat(@PathVariable int id) {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView(CHAT);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("users", serviceUsers.getAll());
            modelAndView.addObject("messages", serviceMessages.getAll());
            modelAndView.addObject("friendship", serviceFriendships.getOne(id));
            return modelAndView;
        } else {
            return new RedirectView("/" + LOGIN);
        }
    }

}
