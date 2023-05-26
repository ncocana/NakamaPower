package edu.craptocraft.nakamapower.service;

import java.util.List;

import edu.craptocraft.nakamapower.entity.Countries;

public interface CountriesService {
    
    public Countries create(Countries country);

    public List<Countries> getAll();

    public Countries getOne(String id);

    public Countries update(String id, Countries country);

    public void delete(String id);

}
