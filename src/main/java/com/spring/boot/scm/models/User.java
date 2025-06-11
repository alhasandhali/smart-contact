package com.spring.boot.scm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name required")
    @Size(min = 2, max = 30, message = "Minimum 2 characters and maximum 20 characters are allowed")
    private String name;
    @Column(unique = true)
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;
    @NotBlank(message = "Password required")
    @Size(min = 6, max = 100, message = "Minimum 2 characters and maximum 15 characters are allowed")
    private String password;
    private String role;
    @Column(length = 500)
    private String about;
    private String imageUrl;
    private boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    public User() {
    }

    public User(int id, String name, String email, String password, String role, String about, String imageUrl, boolean enabled, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.about = about;
        this.imageUrl = imageUrl;
        this.enabled = enabled;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\temail='" + email + '\'' +
                ", \n\tpassword='" + password + '\'' +
                ", \n\trole='" + role + '\'' +
                ", \n\tabout='" + about + '\'' +
                ", \n\timageUrl='" + imageUrl + '\'' +
                ", \n\tenabled=" + enabled +
                ", \n\tcontacts=" + contacts +
                "\n}";
    }
}
