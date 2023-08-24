package com.project.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.UUID;

public class UserFileDataAccessService implements UserDAO {

    private final String PATH = "src/main/resources/users.csv";
    private final File file = new File(PATH);

    @Override
    public User[] getAllUsers() {
        int numberOfUsers = countUsers();
        if(numberOfUsers == 0)
            return new User[0];
        User[] users = new User[numberOfUsers];
        try {
            Scanner sc = new Scanner(file);
            int index = 0;
            String[] line;
            while (sc.hasNext()) {
                line = sc.nextLine().trim().split(",\\s*");
                users[index++] = new User(UUID.fromString(line[0]), line[1]);
            }
            sc.close();
        } catch (FileNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    private int countUsers() {
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                count++;
                sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
