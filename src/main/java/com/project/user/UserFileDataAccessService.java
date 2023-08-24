package com.project.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.UUID;

public class UserFileDataAccessService implements UserDAO {

    private final String PATH = "src/main/resources/users.csv";
    private final File file = new File(PATH);

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            String[] line;
            while (sc.hasNext()) {
                line = sc.nextLine().trim().split(",\\s*");
                users.add(new User(UUID.fromString(line[0]), line[1]));
            }
            sc.close();
        } catch (FileNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
