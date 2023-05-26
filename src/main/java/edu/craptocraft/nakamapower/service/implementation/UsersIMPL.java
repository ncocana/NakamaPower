package edu.craptocraft.nakamapower.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.repository.UsersRepo;
import edu.craptocraft.nakamapower.service.UsersService;

@Service
public class UsersIMPL implements UsersService {

    @Autowired
    private UsersRepo repo;

    @Override
    public Users create(Users user) {
        user.setId(user.getId());
        user.setEmail(user.getEmail());
        user.setUser(user.getUser());
        user.setPassword(user.getPassword());
        user.setLastLog(user.getLastLog());
        user.setSession(user.getSession());
        user.setCountry(user.getCountry());
        return this.repo.save(user);
    }

    @Override
    public List<Users> getAll() {
        return (List<Users>) this.repo.findAll();
    }

    @Override
    public Users getOne(int id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Users update(int id, Users user) {
        user.setId(id);
        user.setEmail(user.getEmail());
        user.setUser(user.getUser());
        user.setPassword(user.getPassword());
        user.setLastLog(user.getLastLog());
        user.setSession(user.getSession());
        user.setCountry(user.getCountry());
        return this.repo.save(user);
    }

    @Override
    public void delete(int id) {
        this.repo.deleteById(id);
    }

}
