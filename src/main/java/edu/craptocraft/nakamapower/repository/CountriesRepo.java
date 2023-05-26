package edu.craptocraft.nakamapower.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.craptocraft.nakamapower.entity.Countries;

@Repository
public interface CountriesRepo extends CrudRepository<Countries, String> {

}
