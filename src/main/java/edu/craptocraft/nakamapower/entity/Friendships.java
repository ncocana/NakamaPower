package edu.craptocraft.nakamapower.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "friendship")
@IdClass(FriendshipId.class)
public class Friendships {
    
    @Id
    @Column(name = "idUser")
    private Users idUser;

    @Id
    @Column(name = "idFriend", unique = true, length = 50)
    private Users idFriend;

    public Friendships() {
    }

    public Friendships(Users idUser, Users idFriend) {
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
        Friendships that = (Friendships) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idFriend, that.idFriend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idFriend);
    }

}
