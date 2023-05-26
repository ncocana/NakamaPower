package edu.craptocraft.nakamapower.service;

import java.util.List;

import edu.craptocraft.nakamapower.entity.Friendships;

public interface FriendshipsService {
    
    public Friendships create(Friendships friendship);

    public List<Friendships> getAll();

    public Friendships getOne(Friendships idUser);

    public Friendships update(Friendships idUser, Friendships friendship);

    public void delete(Friendships idUser);

}
