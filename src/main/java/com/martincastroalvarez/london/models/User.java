package com.martincastroalvarez.london;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.security.spec.InvalidKeySpecException;

@Entity
@Table(name="users")
public class User {
    // --------------------------------------------------------------------
    // User Model.
    //
    // This model represents a User that can login to the application.
    // --------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String password;

    public void User() {}

    public void User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) throws InvalidKeySpecException {
        // --------------------------------------------------------------------
        // Hashing password before saving it into the database.
        //
        // Hashing turns your password (or any other piece of data) into a
        // short string of letters and/or numbers using an encryption algorithm.
        // If a website is hacked, cyber criminals don't get access to your
        // password. Instead, they just get access to the encrypted “hash”
        // created by your password.
        // --------------------------------------------------------------------
        HashUtil hasher = new HashUtil();
        this.password = hasher.hash(password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
