package com.project.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class UserServiceTest {
    UserService userServiceFile = new UserService(new UserFileDataAccessService());
    UserService userServiceArray = new UserService(new UserArrayDataAccessService());

    @Test
    public void shouldGetAllUsersFromFileUsers() {
        List<User> allUsers = userServiceFile.getAllUsers();
        List<User> expectedUsers = List.of(
                new User(UUID.fromString("7e4b9220-a47a-45a7-a33b-7182ee0dc30e"), "Leila"),
                new User(UUID.fromString("0236e9db-8c46-45a1-8fef-718d12e271f3"), "Bond"),
                new User(UUID.fromString("43bf7ab5-1f20-4693-a4f0-7319a7926d66"), "Ali"),
                new User(UUID.fromString("1fda7774-b948-42fa-ad35-7eb1a7248e35"), "Samira")
        );
        Assertions.assertEquals(allUsers, expectedUsers);
    }

    @Test
    public void shouldGetAllUsersFromArray() {
        List<String> names = userServiceArray.getAllUsers().stream()
                .map(User::getName)
                .toList();
        Assertions.assertEquals(names, List.of(
                "User1",
                "User2"
        ));
    }

    @Test
    public void shouldFindUserById() {
        List<User> allUsers = userServiceFile.getAllUsers();
        UUID uuid = allUsers.get(0).getUuid();
        Assertions.assertEquals(userServiceFile.findUserById(uuid), allUsers.get(0));
    }

    @Test
    public void shouldNotFindUserById() {
        UUID uuid = userServiceFile.getAllUsers().get(0).getUuid();
        User user = new User(UUID.fromString("07f1091a-c626-44d5-b66c-5abc6a3dd616"),
                "fakeUser");
        Assertions.assertNotEquals(userServiceFile.findUserById(uuid), user);
    }
}
