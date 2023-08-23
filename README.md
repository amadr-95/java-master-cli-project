# CLI Car Booking Application

## Interfaces

Now all DAO classes have to be interfaces. This is the next stept to do things properly.
_UserDAO_ example:
```
package com.project.user;

import java.util.UUID;

public interface UserDao {
    User[] getUsers();
}
```

The new _UserArrayDataAccessService_ have to implement the _UserDAO_ and override the methods.
It is also required to change the instances in
the other classes from _new UserDAO()_ to _new UserArrayDataAccessService()_, but the reference
to that object has to keep pointing to the interface
