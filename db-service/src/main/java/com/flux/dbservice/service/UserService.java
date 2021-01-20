package com.flux.dbservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flux.dbservice.entity.user.User;
import com.flux.dbservice.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public String saveUser(String userJson) {
        User jsonUser = objectMapper.readValue(userJson, User.class);
        User dbUser = userRepository.findByChatId(jsonUser.getChatId());

        if (!isNull(dbUser)) {
            jsonUser.setId(dbUser.getId());
        }
        return objectMapper.writeValueAsString(userRepository.save(jsonUser));
    }
}