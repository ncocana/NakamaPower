package edu.craptocraft.nakamapower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.service.FriendshipsService;
import jakarta.persistence.NoResultException;

@RestController
@RequestMapping("/friendships")
public class FriendshipsController {

    @Autowired
    private FriendshipsService serviceFriendships;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Friendships friendship) {
        try {
            Friendships createdFriendship = this.serviceFriendships.create(friendship);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFriendship);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Friendships> listDoctors = this.serviceFriendships.getAll();
        return ResponseEntity.ok(listDoctors);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Friendships id) {
        Friendships singleFriendship = this.serviceFriendships.getOne(id);
        return ResponseEntity.ok(singleFriendship);
    }

    @PutMapping(path = "update/user/{idUser}/friend/{idFriend}")
    public ResponseEntity<?> update(@PathVariable int idUser, @PathVariable int idFriend, @RequestBody Friendships friendship) {
        try {
            Friendships updatedFriendship = this.serviceFriendships.update(idUser, idFriend, friendship);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFriendship);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friendship not found");
        }
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Friendships id) {
        this.serviceFriendships.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
