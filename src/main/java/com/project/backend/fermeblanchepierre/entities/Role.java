package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Roles")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idRole",
        scope = Role.class
)
@JsonIgnoreProperties({"user"})
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idRole", nullable = false)
    private Integer idRole;

    @Column(name = "label")
    private String label;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<User> users;

    public Role() {
    }

    public Role(Integer idRole) {
        this.idRole = idRole;
    }


    // GETTERS & SETTERS

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("user")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
