package model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private Role role;

    public User() {}

//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
}
