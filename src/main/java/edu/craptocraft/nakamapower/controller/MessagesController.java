package edu.craptocraft.nakamapower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.nakamapower.entity.Messages;
import edu.craptocraft.nakamapower.service.MessagesService;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService serviceMessages;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Messages message) {
        try {
            Messages createdMessage = this.serviceMessages.create(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Messages> listMessages = this.serviceMessages.getAll();
        return ResponseEntity.ok(listMessages);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Messages singleMessage = this.serviceMessages.getOne(id);
        return ResponseEntity.ok(singleMessage);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Messages message) {
        try {
            Messages updatedMessage = this.serviceMessages.update(id, message);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMessage);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.serviceMessages.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
