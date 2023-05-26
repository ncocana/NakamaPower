package edu.craptocraft.nakamapower.service;

import java.util.List;

import edu.craptocraft.nakamapower.entity.Friendships;

public interface FriendshipService {
    
    public Friendships create(Friendships friendship);

    public List<Friendships> getAll();

    public Friendships getOne(int id);

    public Friendships update(int id, Friendships friendship);

    public void delete(int id);

}
