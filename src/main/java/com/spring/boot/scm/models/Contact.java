package com.spring.boot.scm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nickName;
    private String email;
    private String work;
    private String phone;
    @Transient
    private MultipartFile profileImage;
    private String image;
    @Column(length = 1000)
    private String description;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Contact() {
    }

    public Contact(int id, String name, String nickName, String email, String work, String phone, String image, String description, User user) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.work = work;
        this.phone = phone;
        this.image = image;
        this.description = description;
        this.user = user;
    }

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{\n" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tnickName='" + nickName + '\'' +
                ", \n\temail='" + email + '\'' +
                ", \n\twork='" + work + '\'' +
                ", \n\tphone='" + phone + '\'' +
                ", \n\tprofileImage=" + profileImage +
                ", \n\timage='" + image + '\'' +
                ", \n\tdescription='" + description + '\'' +
                ", \n\tuser=" + user +
                "\n}";
    }
}
