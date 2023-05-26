package edu.craptocraft.nakamapower.entity;

import java.io.Serializable;
import java.util.Objects;

import edu.craptocraft.nakamapower.service.implementation.FriendshipsIMPL;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friendship")
@IdClass(FriendshipsId.class)
public class Friendships implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users idUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "idFriend")
    private Users idFriend;

    public Friendships() {
    }

    public Friendships(Users idUser, Users idFriend) {
        this.idUser = idUser;
        this.idFriend = idFriend;
    }

    public Friendships(int idUser, int idFriend) {
        this.idUser = FriendshipsIMPL.getUser(idUser);
        this.idFriend = FriendshipsIMPL.getUser(idFriend);
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
