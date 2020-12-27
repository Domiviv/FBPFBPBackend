package com.project.backend.fermeblanchepierre.entities;

import com.fasterxml.jackson.annotation.*;
import com.project.backend.fermeblanchepierre.services.Password;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Users")
@JsonIgnoreProperties({"order"})
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Integer idUser;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "verified")
    private boolean verified;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idRole")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Order> orders;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        Password p = new Password(pwd);
        p.firstHash();
        this.pwd = p.getHash();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonProperty("order")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


}

