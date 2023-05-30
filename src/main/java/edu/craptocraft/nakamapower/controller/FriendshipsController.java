package edu.craptocraft.nakamapower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.service.FriendshipsService;

@RestController
@RequestMapping("/friendships")
public class FriendshipsController {

    @Autowired
    private FriendshipsService serviceFriendships;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Friendships friendship) {
        try {
            List<Friendships> listFriendships = this.serviceFriendships.getAll();

            boolean friendshipExists = listFriendships.stream()
                    .anyMatch(friend -> (friend.getIdFriend().getId() == friendship.getIdFriend().getId() || friend.getIdFriend().getId() == friendship.getIdUser().getId())
                            && (friend.getIdUser().getId() == friendship.getIdFriend().getId() || friend.getIdUser().getId() == friendship.getIdUser().getId()));

            if (friendshipExists) {
                return ResponseEntity.badRequest().body("This friendship already exists.");
            }

            Friendships createdFriendship = this.serviceFriendships.create(friendship);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFriendship);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Friendships> listFriendships = this.serviceFriendships.getAll();
        return ResponseEntity.ok(listFriendships);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Friendships singleFriendship = this.serviceFriendships.getOne(id);
        return ResponseEntity.ok(singleFriendship);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Friendships friendship) {
        try {
            Friendships updatedFriendship = this.serviceFriendships.update(id, friendship);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFriendship);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.serviceFriendships.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
