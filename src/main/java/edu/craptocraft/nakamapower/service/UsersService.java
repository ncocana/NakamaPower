package edu.craptocraft.nakamapower.service;

import java.util.List;

import edu.craptocraft.nakamapower.entity.Users;

public interface UsersService {
    
    public Users create(Users user);

    public List<Users> getAll();

    public Users getOne(int id);

    public Users update(int id, Users user);

    public void delete(int id);

}
