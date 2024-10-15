package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
}
