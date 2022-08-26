package com.example;

import com.example.dao.UserRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoSecurityJwtApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityJwtApplication.class, args);
    }

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("123456"));
//        userRepository.save(user);
    }
}
