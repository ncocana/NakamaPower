package edu.craptocraft.nakamapower.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.repository.FriendshipsRepo;
import edu.craptocraft.nakamapower.service.FriendshipsService;

@Service
public class FriendshipsIMPL implements FriendshipsService {

    @Autowired
    private FriendshipsRepo repo;

    @Override
    public Friendships create(Friendships friendship) {
        friendship.setIdUser(friendship.getIdUser());
        friendship.setIdFriend(friendship.getIdFriend());
        return this.repo.save(friendship);
    }

    @Override
    public List<Friendships> getAll() {
        return (List<Friendships>) this.repo.findAll();
    }

    @Override
    public Friendships getOne(Friendships idUser) {
        return this.repo.findById(idUser.getIdUser()).orElse(null);
    }

    @Override
    public Friendships update(Friendships idUser, Friendships friendship) {
        friendship.setIdUser(idUser.getIdUser());
        friendship.setIdFriend(friendship.getIdFriend());
        return this.repo.save(friendship);
    }

    @Override
    public void delete(Friendships idUser) {
        this.repo.deleteById(idUser.getIdUser());
    }

}
