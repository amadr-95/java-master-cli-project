# CLI Car Booking Application

## Interfaces (File Version)

Now all DAO classes have to be interfaces. This is the next step to do things properly.
_UserDAO_ example:
```
package com.project.user;

import java.util.UUID;

public interface UserDao {
    User[] getUsers();
}
```

In this case we go one step further and the users are going to be read from a given csv file.
```
7e4b9220-a47a-45a7-a33b-7182ee0dc30e, Leila
0236e9db-8c46-45a1-8fef-718d12e271f3, Bond
43bf7ab5-1f20-4693-a4f0-7319a7926d66, Ali
1fda7774-b948-42fa-ad35-7eb1a7248e35, Samira
```

To do so, the new _UserFileDataAccessService_ class have to implement the _UserDAO_ and override
the methods. This class will be in charge to read each user and parse him to an User Object.

The booking data is also saved in a file. There is a new method in BookingService class that saves the booking
when is made.