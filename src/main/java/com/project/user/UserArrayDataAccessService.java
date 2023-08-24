package com.project.user;

import java.util.List;

public class UserArrayDataAccessService implements UserDAO {
    private static final List<User> users;

    static {
        users = List.of(
                new User("User1"),
                new User("User2")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

}
