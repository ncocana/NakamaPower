package edu.craptocraft.nakamapower.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.craptocraft.nakamapower.entity.FriendshipsId;
import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Users;

@Repository
public interface FriendshipsRepo extends CrudRepository<Friendships, FriendshipsId> {

}
