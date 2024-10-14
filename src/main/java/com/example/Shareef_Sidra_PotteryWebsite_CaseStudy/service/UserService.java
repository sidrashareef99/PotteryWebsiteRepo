package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.User;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder PasswordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


//    public User login(String username, String password) {
//        User user = userRepository.findByUsername(username);
//
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }
//
//    public User register(String username, String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//        User newUser = new User(username, encodedPassword);
//        return userRepository.save(newUser);
//    }
//    public void delete(User user) {
//        userRepository.delete(user);
//    }
//    public User update(Long id, User updatedUser) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setFirstName(updatedUser.getFirstName());
//                    user.setLastName(updatedUser.getLastName());
//                    user.setEmail(updatedUser.getEmail());
//                    user.setPassword(updatedUser.getPassword());
//                    user.setRole(updatedUser.getRole());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//    }
}
