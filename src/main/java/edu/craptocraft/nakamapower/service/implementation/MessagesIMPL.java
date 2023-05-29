package edu.craptocraft.nakamapower.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Messages;
import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.repository.FriendshipsRepo;
import edu.craptocraft.nakamapower.repository.MessagesRepo;
import edu.craptocraft.nakamapower.repository.UsersRepo;
import edu.craptocraft.nakamapower.service.MessagesService;

@Service
public class MessagesIMPL implements MessagesService {

    @Autowired
    private MessagesRepo repo;

    private static UsersRepo repoUsers;
    private static FriendshipsRepo repoFriendships;

    @Override
    public Messages create(Messages message) {
        message.setId(message.getId());
        message.setChat(message.getChat());
        message.setSender(message.getSender());
        message.setReceptor(message.getReceptor());
        message.setText(message.getText());
        message.setDate(LocalDate.now());
        return this.repo.save(message);
    }

    @Override
    public List<Messages> getAll() {
        return (List<Messages>) this.repo.findAll();
    }

    @Override
    public Messages getOne(int id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Messages update(int id, Messages message) {
        message.setId(id);
        message.setChat(message.getChat());
        message.setSender(message.getSender());
        message.setReceptor(message.getReceptor());
        message.setText(message.getText());
        message.setDate(LocalDate.now());
        return this.repo.save(message);
    }

    @Override
    public void delete(int id) {
        this.repo.deleteById(id);
    }

    public static Users getUser(int id) {
        return repoUsers.findById(id).orElse(null);
    }

    public static Friendships getFriendship(int id) {
        return repoFriendships.findById(id).orElse(null);
    }

}
