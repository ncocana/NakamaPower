package edu.craptocraft.nakamapower.service;

import edu.craptocraft.nakamapower.entity.Users;

public interface LoginService {
    
    boolean login(String email, String password);

    boolean isLoggedIn();
    
    Users getUser();

    void logOut();
    
}
