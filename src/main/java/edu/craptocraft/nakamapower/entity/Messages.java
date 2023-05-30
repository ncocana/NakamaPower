package edu.craptocraft.nakamapower.entity;

import java.time.LocalDate;
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
    @JoinColumn(name = "chat", nullable = false)
    private Friendships chat;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receptor", nullable = false)
    private Users receptor;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private LocalDate date;

    public Messages() {
    }

    public Messages(Friendships chat, String text) {
        this.chat = chat;
        this.sender = chat.getIdUser();
        this.receptor = chat.getIdFriend();
        this.text = text;
        this.date = null;
    }

    public Messages(Friendships chat, Users sender, Users receptor, String text) {
        this.chat = chat;
        this.sender = sender;
        this.receptor = receptor;
        this.text = text;
        this.date = null;
    }

    public Messages(int idChat, int idSender, int idReceptor, String text) {
        this.chat = MessagesIMPL.getFriendship(idChat);
        this.sender = MessagesIMPL.getUser(idSender);
        this.receptor = MessagesIMPL.getUser(idReceptor);
        this.text = text;
        this.date = null;
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

    public Friendships getChat() {
        return this.chat;
    }

    public void setChat(Friendships chat) {
        this.chat = chat;
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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

