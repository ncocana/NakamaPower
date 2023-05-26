package edu.craptocraft.nakamapower.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.nakamapower.entity.Countries;
import edu.craptocraft.nakamapower.repository.CountriesRepo;
import edu.craptocraft.nakamapower.service.CountriesService;

@Service
public class CountriesIMPL implements CountriesService {

    @Autowired
    private CountriesRepo repo;

    @Override
    public Countries create(Countries country) {
        country.setCode(country.getCode());
        country.setCountry(country.getCountry());
        return this.repo.save(country);
    }

    @Override
    public List<Countries> getAll() {
        return (List<Countries>) this.repo.findAll();
    }

    @Override
    public Countries getOne(String id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Countries update(String id, Countries country) {
        country.setCode(id);
        country.setCountry(country.getCountry());
        return this.repo.save(country);
    }

    @Override
    public void delete(String id) {
        this.repo.deleteById(id);
    }

}
