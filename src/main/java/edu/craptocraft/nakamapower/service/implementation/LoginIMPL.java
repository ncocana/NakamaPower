package edu.craptocraft.nakamapower.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.LoginService;
import edu.craptocraft.nakamapower.service.UsersService;

@Service
public class LoginIMPL implements LoginService {

    @Autowired
    private UsersService serviceUsers;

    private boolean isLogged = false;
    private Users userLogged = null;
    private Random random = null;

    @Override
    public boolean login(String email, String password) {
        List<Users> users = serviceUsers.getAll();
        for(Users user : users) {
            if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                user.setLastLog(LocalDate.now());

                int maxRetries = 100; // Maximum number of retries
                int retryCount = 0; // Counter for retries
                boolean success = false;
                while (!success && retryCount < maxRetries) {
                    try {
                        random = new Random();
                        Integer session = random.nextInt(1000000000);
                        user.setSession(session);
                        serviceUsers.update(user.getId(), user);
                        success = true; // If no exception is thrown, set success to true
                    } catch (DataIntegrityViolationException e) {
                        // Handle the exception if needed
                        retryCount++; // Increment the retry count
                    }
                }

                this.setLoggedIn(true);
                this.setUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        return this.isLogged;
    }

    private void setLoggedIn(boolean value) {
        this.isLogged = value;
    }

    @Override
    public Users getUser() {
        return this.userLogged;
    }

    private void setUser(Users user) {
        this.userLogged = user;
    }

    @Override
    public void logOut() {
        this.isLogged = false;
        this.userLogged.setSession(null);
        serviceUsers.update(userLogged.getId(), userLogged);
        this.userLogged = null;
        this.random = null;
    }
    
}
