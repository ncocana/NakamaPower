package edu.craptocraft.nakamapower.entity;

import java.time.LocalDate;
import java.util.Objects;

import edu.craptocraft.nakamapower.service.implementation.UsersIMPL;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", unique = true, length = 50)
    private String email;
    
    @Column(name = "user", length = 50)
    private String user;

    @Column(name = "password", length = 50)
    private String password;
    
    @Column(name = "lastLog")
    private LocalDate lastLog;
    
    @Column(name = "session", unique = true, length = 9)
    private Integer session;
    
    @ManyToOne
    @JoinColumn(name = "idCountry", nullable = false)
    private Countries idCountry;

    public Users() {}

    public Users(String email, String user, String password, Countries idCountry) {
        this.email = email;
        this.user = user;
        this.password = password;
        this.lastLog = null;
        this.session = null;
        this.idCountry = idCountry;
    }

    public Users(String email, String user, String password, String idCountry) {
        this.email = email;
        this.user = user;
        this.password = password;
        this.lastLog = null;
        this.session = null;
        this.idCountry = UsersIMPL.getCountry(idCountry);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastLog() {
        return this.lastLog;
    }

    public void setLastLog(LocalDate lastLog) {
        this.lastLog = lastLog;
    }

    public Integer getSession() {
        return this.session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public Countries getIdCountry() {
        return this.idCountry;
    }

    public void setIdCountry(Countries idCountry) {
        this.idCountry = idCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Users that = (Users) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
