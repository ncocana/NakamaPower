package edu.craptocraft.nakamapower.entity;

import java.io.Serializable;
import java.util.Objects;

public class FriendshipsId implements Serializable {
    
    private Users idUser;
    private Users idFriend;

    public FriendshipsId() {
    }

    public FriendshipsId(Users idUser, Users idFriend) {
        this.idUser = idUser;
        this.idFriend = idFriend;
    }

    public Users getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public Users getIdFriend() {
        return this.idFriend;
    }

    public void setIdFriend(Users idFriend) {
        this.idFriend = idFriend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FriendshipsId that = (FriendshipsId) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idFriend, that.idFriend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idFriend);
    }
}
