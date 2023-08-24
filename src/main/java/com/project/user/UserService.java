package com.project.user;

import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserDAO userFileDataAccessService;

    public UserService(UserDAO userFileDataAccessService) {
        this.userFileDataAccessService = userFileDataAccessService;
    }

    public List<User> getAllUsers() {
        return userFileDataAccessService.getAllUsers();
    }

    public User findUserById(UUID uuid) {
        return getAllUsers().stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }
}
