package com.example.io.service;

import com.example.io.entity.UserEntity;
import com.example.io.exception.FileEmptyException;
import com.example.io.exception.NumberFieldException;
import com.example.io.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private String pathToReadUsers = "C:\\Users\\Vladyslav\\Desktop\\projectFile.txt";
    private String pathToWriteUsers = "C:\\Users\\Vladyslav\\Desktop\\UsersList.txt";
    private int numberOfSlash = 3;
    private final UserRepository userRepository;

    public void operationWithUser() {
        try {
            List<String> userList = Files.lines(Path.of(pathToReadUsers)).toList();
            String userFromFile;
            for (String line : userList) {
                userFromFile = line;
                checkFileIsEmpty(userFromFile);
                checkSlash(userFromFile);
                findIndex(userFromFile);
                List<String> split = List.of(userFromFile.split("/"));
                UserEntity user = new UserEntity();
                user.setName(split.get(0));
                user.setSurname(split.get(1));
                user.setNickname(split.get(2));
                user.setPhoneNumber(split.get(3));
                userRepository.save(user);
            }
        } catch (IOException exception) {
            exception.getMessage();
        }
    }

    private void checkSlash(String line) {
        int count = StringUtils.countMatches(line, '/');
        if (count != numberOfSlash) {
            throw new NumberFieldException();
        }
    }

    private void checkFileIsEmpty(String file) {
        if (file.length() == 0) {
            throw new FileEmptyException();
        }
    }

    private void findIndex(String line) {
        StringBuilder builder = new StringBuilder(line);
        String elementsOfLine;
        for (int i = 0; i <= numberOfSlash; i++) {
            if (i != numberOfSlash) {
                elementsOfLine = builder.substring(0, builder.indexOf("/"));
                builder.delete(0, builder.indexOf("/") + 1);
                if (!StringUtils.isAlpha(elementsOfLine)) {
                    throw new RuntimeException("Invalid symbol characters");
                }
            } else {
                if (!StringUtils.isNumeric(builder)) {
                    throw new RuntimeException("Invalid number characters");
                }
            }
        }
    }

    public void getUsers() {
        List<UserEntity> userList = userRepository.findAll();
        String amount = String.valueOf(userList.size());
        Path path = Path.of(pathToWriteUsers);
        try {
            Files.createFile(path);
            Files.writeString(path, amount + "\n", StandardOpenOption.APPEND);
            for (UserEntity user : userList) {
                String users = user.getName() + " "
                        + user.getSurname() + " "
                        + user.getNickname() + " "
                        + user.getPhoneNumber() + "\n";
                Files.writeString(path, users, StandardOpenOption.APPEND);
            }
        } catch (IOException exception) {
            exception.getMessage();
        }
    }
}
