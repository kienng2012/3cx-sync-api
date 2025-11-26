package com.java.schedule;

import com.java.entity.Users;
import com.java.repository.UsersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Log4j2
public class ReloadUser {

    public static Map<String, Users> mapUsers = new HashMap<>();
    @Autowired
    private UsersRepository usersRepository;

    @Scheduled(initialDelayString = "${users.async.initial-delay}", fixedDelayString = "${users.async.time-cache}")
    public void reloadUser() {
        Optional<List<Users>> optionalUsers = usersRepository.getAllUsers();
        if (optionalUsers.isPresent()) {
            List<Users> lstUser = optionalUsers.get();
            lstUser.stream().forEach(users -> mapUsers.put(users.getUsername(), users));
        }
    }
}
