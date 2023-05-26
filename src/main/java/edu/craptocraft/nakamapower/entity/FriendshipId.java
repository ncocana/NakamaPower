package edu.craptocraft.nakamapower.entity;

import java.io.Serializable;
import java.util.Objects;

public class FriendshipId implements Serializable {
    
    private Users idUser;
    private Users idFriend;

    public FriendshipId() {
    }

    public FriendshipId(Users idUser, Users idFriend) {
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
        FriendshipId that = (FriendshipId) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idFriend, that.idFriend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idFriend);
    }
}
