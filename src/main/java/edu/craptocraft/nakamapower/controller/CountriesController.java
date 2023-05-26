package edu.craptocraft.nakamapower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.nakamapower.entity.Countries;
import edu.craptocraft.nakamapower.service.CountriesService;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountriesService serviceCountries;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Countries country) {
        try {
            Countries createdCountry = this.serviceCountries.create(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Countries> listDoctors = this.serviceCountries.getAll();
        return ResponseEntity.ok(listDoctors);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        Countries singleCountry = this.serviceCountries.getOne(id);
        return ResponseEntity.ok(singleCountry);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Countries country) {
        Countries updatedCountry = this.serviceCountries.update(id, country);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCountry);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        this.serviceCountries.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
