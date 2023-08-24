# CLI Car Booking Application

## Dependency Injection

Now it is time to use Dependency Injection in our codebase.
_UserService_ example:
```
package com.project.user;

import java.util.UUID;

public class UserService {

    private final UserDAO userFileDataAccessService;

    public UserService(UserDAO userFileDataAccessService) {
        this.userFileDataAccessService = userFileDataAccessService;
    }
```
Main class:

```
//dependencies
private static final UserDAO userDAO = new userFileDataAccessService();
private static final CarDAO carDAO = new CarArrayDataAccessService();
private static final BookingDAO bookingDAO = new BookingArrayDataAccessService();

//injection
private static final UserService userService = new UserService(userDAO);
private static final CarService carService = new CarService(carDAO);
private static final BookingService bookingService = new BookingService
    (
        bookingDAO,
        carService,
        userService
    );
```