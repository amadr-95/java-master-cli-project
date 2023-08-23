package com.project.user;

public class UserDAO {
    private static final User[] users;

    static {
        users = new User[]{
                new User("User1"),
                new User("User2"),
                new User("User3"),
                new User("User4")
        };
    }

    public User[] getAllUsers() {
        return users;
    }

}
