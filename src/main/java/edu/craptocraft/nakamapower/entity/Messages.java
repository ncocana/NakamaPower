package edu.craptocraft.nakamapower.entity;

import java.util.Objects;

import edu.craptocraft.nakamapower.service.implementation.MessagesIMPL;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Messages {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receptor", nullable = false)
    private Users receptor;

    @Column(name = "text")
    private String text;

    public Messages() {
    }

    public Messages(Users sender, Users receptor, String text) {
        this.sender = sender;
        this.receptor = receptor;
        this.text = text;
    }

    public Messages(int idSender, int idReceptor, String text) {
        this.sender = MessagesIMPL.getUser(idSender);
        this.receptor = MessagesIMPL.getUser(idReceptor);
        this.text = text;
    }

    public Messages(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getSender() {
        return this.sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceptor() {
        return this.receptor;
    }

    public void setReceptor(Users receptor) {
        this.receptor = receptor;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Messages that = (Messages) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}

