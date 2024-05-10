package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String avatarPath;
    private static final ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;

    public User(String username, String password, String avatarPath) {
        this.username = username;
        this.password = password;
        this.avatarPath = avatarPath;
        users.add(this);
    }

    public static void removeUser(User loggedInUser) {
        users.remove(loggedInUser);
        loggedInUser.setUsername(loggedInUser.getUsername() + "(deleted)");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
