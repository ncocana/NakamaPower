package edu.craptocraft.nakamapower.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class Countries {
    
    @Id
    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "country", unique = true, length = 100)
    private String country;

    public Countries() {
    }

    public Countries(String code, String country) {
        this.code = code;
        this.country = country;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Countries that = (Countries) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

}
