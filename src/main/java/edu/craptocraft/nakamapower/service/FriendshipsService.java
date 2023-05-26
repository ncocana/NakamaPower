package edu.craptocraft.nakamapower.service;

import java.util.List;

import edu.craptocraft.nakamapower.entity.Friendships;

public interface FriendshipsService {
    
    public Friendships create(Friendships friendship);

    public List<Friendships> getAll();

    public Friendships getOne(Friendships idUser);

    public Friendships update(int idUser, int idFriend, Friendships friendship);

    public void delete(Friendships idUser);

}
