package com.project.user;

public class UserArrayDataAccessService implements UserDAO {
    private static final User[] users;

    static {
        users = new User[]{
                new User("User1"),
                new User("User2"),
        };
    }

    @Override
    public User[] getAllUsers() {
        return users;
    }

}
