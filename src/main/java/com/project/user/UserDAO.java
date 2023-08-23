package com.project.user;

import java.util.Arrays;

public class UserDAO {
    private static final int CAPACITY = 5;
    private static User[] users;
    private static int nextAvailableSlot;

    static {
        users = new User[CAPACITY];
        users[0] = new User("User1");
        users[1] = new User("User2");
        nextAvailableSlot = 2;
    }

    public User[] getAllUsers() {
        int number = numberOfUsers();
        if (number == 0)
            return new User[0];
        else if (number == users.length) {
            return users;
        }

        User[] getUsers = new User[number];
        int index = 0;
        for (User user : users) {
            if (user != null)
                getUsers[index++] = user;
        }
        return getUsers;
    }

    private int numberOfUsers() {
        int count = 0;
        for (User user : users) {
            if (user != null)
                count++;
        }
        return count;
    }

    public void saveUser(User user) {
        if (nextAvailableSlot >= CAPACITY)
            users = growUsers();
        users[nextAvailableSlot++] = user;
    }

    private User[] growUsers() {
        return Arrays.copyOf(users, users.length + 1);
    }

}