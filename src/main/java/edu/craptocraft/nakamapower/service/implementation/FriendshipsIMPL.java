package edu.craptocraft.nakamapower.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.repository.FriendshipsRepo;
import edu.craptocraft.nakamapower.repository.UsersRepo;
import edu.craptocraft.nakamapower.service.FriendshipsService;

@Service
public class FriendshipsIMPL implements FriendshipsService {

    @Autowired
    private FriendshipsRepo repo;

    private static UsersRepo repoUsers;

    @Override
    public Friendships create(Friendships friendship) {
        friendship.setId(friendship.getId());
        friendship.setIdUser(friendship.getIdUser());
        friendship.setIdFriend(friendship.getIdFriend());
        return this.repo.save(friendship);
    }

    @Override
    public List<Friendships> getAll() {
        return (List<Friendships>) this.repo.findAll();
    }

    @Override
    public Friendships getOne(int id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Friendships update(int id, Friendships friendship) {
        friendship.setId(id);
        friendship.setIdUser(friendship.getIdUser());
        friendship.setIdFriend(friendship.getIdFriend());
        return this.repo.save(friendship);
    }

    @Override
    public void delete(int id) {
        this.repo.deleteById(id);
    }

    public static Users getUser(int id) {
        return repoUsers.findById(id).orElse(null);
    }

}
