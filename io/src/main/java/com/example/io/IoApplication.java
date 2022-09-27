package com.example.io;

import com.example.io.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class IoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IoApplication.class, args);
    }

    final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.getUsers();
    }
}
