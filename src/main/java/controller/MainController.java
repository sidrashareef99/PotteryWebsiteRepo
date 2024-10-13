package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import service.UserService;

@Controller
public class MainController {
        @Autowired
        private UserService userService;
        @Autowired
        private PasswordEncoder passwordEncoder;


        @GetMapping("/")
        public String getHome(){
            return "index";
        }

        @GetMapping("/login")
        public String getLogin(){
            return "login";
        }

        @GetMapping("/register")
        public String getRegister(){
            return "register";
        }

}

