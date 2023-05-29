package edu.craptocraft.nakamapower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.craptocraft.nakamapower.entity.Login;
import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.CountriesService;
import edu.craptocraft.nakamapower.service.FriendshipsService;
import edu.craptocraft.nakamapower.service.LoginService;
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

    private Users currentUser = null;

    private static final String LOGIN = "login";
    private static final String SIGN_UP = "sign-up";
    private static final String FRIENDSHIPS = "friendships";
    private static final String FRIENDSHIPS_CREATE = "friendships-create";
    private static final String FRIENDSHIPS_UPDATE = "friendships-update";

    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView(LOGIN);
    }

    @GetMapping("/login")
    public Object login() {
        if (currentUser != null) {
            return new RedirectView(FRIENDSHIPS);
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
            return new ModelAndView(SIGN_UP);
        }
    }

    @GetMapping("/friendships")
    public Object prescriptionsManagement() {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView(FRIENDSHIPS);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("friendships", serviceFriendships.getAll());
            return modelAndView;
        } else {
            return new RedirectView(LOGIN);
        }
    }

    @GetMapping("/friendships/create")
    public Object discharge() {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(FRIENDSHIPS_CREATE);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("users", serviceUsers.getAll());
            modelAndView.addObject("countries", serviceCountries.getAll());
            return modelAndView;
        } else {
            return new RedirectView(LOGIN);
        }
    }

    @GetMapping("/friendships/update/{id}")
    public Object prescriptionsManagementUpdate(@PathVariable int id) {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView(FRIENDSHIPS_UPDATE);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("users", serviceUsers.getAll());
            modelAndView.addObject("countries", serviceCountries.getAll());
            modelAndView.addObject("friendships", serviceFriendships.getOne(id));
            return modelAndView;
        } else {
            return new RedirectView(LOGIN);
        }
    }

    @GetMapping("/friendships/delete/{id}")
    public Object prescriptionsManagementDelete(@PathVariable int id) {
        if (currentUser != null) {
            serviceFriendships.delete(id);
            return new RedirectView(FRIENDSHIPS);
        } else {
            return new RedirectView(LOGIN);
        }
    }

}
