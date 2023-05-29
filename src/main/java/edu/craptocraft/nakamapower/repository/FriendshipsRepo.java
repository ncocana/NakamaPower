package edu.craptocraft.nakamapower.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.craptocraft.nakamapower.entity.Friendships;

@Repository
public interface FriendshipsRepo extends CrudRepository<Friendships, Integer> {

}
