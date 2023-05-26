package edu.craptocraft.nakamapower.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.craptocraft.nakamapower.entity.Friendship;
import edu.craptocraft.nakamapower.entity.Users;

@Repository
public interface FriendshipRepo extends CrudRepository<Friendship, Users> {

}
