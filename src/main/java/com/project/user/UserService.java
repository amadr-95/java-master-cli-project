package com.project.user;

import java.util.UUID;

public class UserService {

    private final UserDAO userFileDataAccessService;

    public UserService() {
        this.userFileDataAccessService = new UserFileDataAccessService();
    }

    public User[] getAllUsers() {
        return userFileDataAccessService.getAllUsers();
    }

    public User findUserById(UUID uuid) {
        for (User user : getAllUsers()) {
            if (user.getUuid().equals(uuid))
                return user;
        }
        return null;
    }
}
