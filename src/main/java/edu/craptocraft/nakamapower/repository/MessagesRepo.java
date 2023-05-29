package edu.craptocraft.nakamapower.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.craptocraft.nakamapower.entity.Messages;

@Repository
public interface MessagesRepo extends CrudRepository<Messages, Integer> {

}
