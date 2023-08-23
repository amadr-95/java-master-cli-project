package com.project.user;

import java.util.UUID;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User[] getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User findUserById(UUID uuid) {
        for (User user : getAllUsers()) {
            if (user.getUuid().equals(uuid))
                return user;
        }
        return null;
    }
}
