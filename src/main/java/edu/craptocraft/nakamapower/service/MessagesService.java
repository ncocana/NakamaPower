package edu.craptocraft.nakamapower.service;

import java.util.List;

import edu.craptocraft.nakamapower.entity.Messages;

public interface MessagesService {
    
    public Messages create(Messages message);

    public List<Messages> getAll();

    public Messages getOne(int id);

    public Messages update(int id, Messages message);

    public void delete(int id);

}
