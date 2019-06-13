package com.ayudape.developer.chatbot.model;

import com.ayudape.developer.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(name = "first_name",  nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    public User(){

    }

    public User(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {return phone;}

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + getUuid() +
                "firstName='" + getFirstName() +
                "\'lastName=\'" + getLastName() +
                "'}";
    }
}
