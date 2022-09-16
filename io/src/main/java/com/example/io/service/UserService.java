package com.example.io.service;

import com.example.io.entity.UserEntity;
import com.example.io.exception.FileEmptyException;
import com.example.io.exception.NumberFieldException;
import com.example.io.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void operationWithUser() {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("C:\\Users\\Vladyslav\\Desktop\\projectFile.txt"))) {
            String userFromFile;
            while ((userFromFile = bufferedReader.readLine()) != null) {
                checkFileIsEmpty(userFromFile);
                checkSlash(userFromFile);
                findIndex(userFromFile);
                String[] split = userFromFile.split("/");
                UserEntity user = new UserEntity();
                user.setName(split[0]);
                user.setSurname(split[1]);
                user.setNickname(split[2]);
                user.setPhoneNumber(split[3]);
//                System.out.println(Arrays.toString(split));
                userRepository.save(user);
            }
        } catch (IOException exception) {
            exception.getMessage();
        }
    }

    private void checkSlash(String line) {
        int count = StringUtils.countMatches(line, '/');
        if (count != 3) {
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
        System.out.println(builder);
        char[] str;
        for (int i = 0; i <= 3; i++) {
            if (i != 3) {
                str = builder.substring(0, builder.indexOf("/")).toCharArray();
                builder.delete(0, builder.indexOf("/") + 1);
                for (int j = 0; j < str.length; j++) {
                    if (!Character.isAlphabetic(str[j])) {
                        throw new RuntimeException("Invalid symbol characters");
                    }
                }
            } else {
                str = builder.substring(0).toCharArray();
                for (int k = 0; k < str.length; k++) {
                    if (!Character.isDigit(str[k])) {
                        throw new RuntimeException("Invalid number characters");
                    }
                }
            }
            System.out.println(Arrays.toString(str));
        }
    }

    public void getUsers() {
        List<UserEntity> userList = userRepository.findAll();
        int amount = userList.size();
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\Vladyslav\\Desktop\\UsersList.txt"))) {
            writer.write(amount + "\n");
            for (UserEntity user : userList) {
                writer.write(
                        user.getName() + " "
                                + user.getSurname() + " "
                                + user.getNickname() + " "
                                + user.getPhoneNumber() + "\n");
            }
        } catch (IOException exception) {
            exception.getMessage();
        }
    }
}
