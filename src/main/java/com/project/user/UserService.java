package com.project.user;

import java.util.UUID;

public class UserService {

    private final UserDAO userArrayDataAccessService;

    public UserService() {
        this.userArrayDataAccessService = new UserArrayDataAccessService();
    }

    public User[] getAllUsers() {
        return userArrayDataAccessService.getAllUsers();
    }

    public User findUserById(UUID uuid) {
        for (User user : getAllUsers()) {
            if (user.getUuid().equals(uuid))
                return user;
        }
        return null;
    }
}
