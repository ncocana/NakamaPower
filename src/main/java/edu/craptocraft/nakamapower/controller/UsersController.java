package edu.craptocraft.nakamapower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService serviceUsers;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Users user) {
        try {
            Users createdUser = this.serviceUsers.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage() + "\nThe email or session are already in use.");
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Users> listUsers = this.serviceUsers.getAll();
        return ResponseEntity.ok(listUsers);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Users singleUser = this.serviceUsers.getOne(id);
        return ResponseEntity.ok(singleUser);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Users user) {
        Users updatedUser = this.serviceUsers.update(id, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.serviceUsers.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
