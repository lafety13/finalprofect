package ua.booking.entities;

import java.util.Objects;

public class UserManager {
    private User authUser = null;

    public UserManager() {
    }

    public UserManager(User user) {
        this.authUser = user;
    }

    public User getUser() throws AuthException {
        if (Objects.isNull(authUser)) throw new AuthException("User is not authorized");
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public boolean isAuthUser() {
        return !Objects.isNull(authUser);
    }
}
