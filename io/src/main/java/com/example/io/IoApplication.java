package com.example.io;

import com.example.io.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class IoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(IoApplication.class, args);
    }

    final UserService userService;

    @Override
    public void run(ApplicationArguments arg0) throws Exception{
        userService.operationWithUser();
    }
}
