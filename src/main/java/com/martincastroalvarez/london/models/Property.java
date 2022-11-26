package com.martincastroalvarez.london;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="properties")
public class Property {
    // --------------------------------------------------------------------
    // Property Model.
    //
    // This model represents a Property in the application that can own
    // be bought by Persons.
    // --------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @JoinColumn(name="fk_owner", nullable=false)
    @ManyToOne(optional=false, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Person owner;

    private String name;

    public void Property() {}

    public void Property(Long id, String name, Person owner) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }

    public Long getId() { return id; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
        final Property other = (Property) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!this.getOwner().equals(other.getOwner())) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Property{");
        sb.append("id=").append(id);
        sb.append(", owner='").append(owner.getName()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
