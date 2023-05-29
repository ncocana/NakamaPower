package edu.craptocraft.nakamapower.entity;

import java.util.Objects;

import edu.craptocraft.nakamapower.service.implementation.FriendshipsIMPL;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friendship")
public class Friendships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private Users idUser;

    @ManyToOne
    @JoinColumn(name = "idFriend", nullable = false)
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
