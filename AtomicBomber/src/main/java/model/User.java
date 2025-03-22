package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String avatarPath;
    private static final ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;
    private int form;
    private final GameSetting gameSetting;
    private boolean blackAndWhite = false;
    private boolean mute = false;
    private int highestScore = -10;
    private int mostKills;
    private int lastWave;

    public User(String username, String password, String avatarPath) {
        this.username = username;
        this.password = password;
        this.avatarPath = avatarPath;
        form = 1;
        gameSetting = new GameSetting();
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

    public GameSetting getGameSetting() {
        return gameSetting;
    }

    public int getForm() {
        return form;
    }

    public void changeKeys() {
        if (form == 1) {
            form = 2;
        } else if (form == 2) {
            form = 1;
        }
        gameSetting.changeKeys(form);
    }

    public void changeColors() {
        gameSetting.changeColors();
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public boolean isMute() {
        return mute;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setMostKills(int mostKills) {
        this.mostKills = mostKills;
    }

    public int getMostKills() {
        return mostKills;
    }

    public void setLastWave(int lastWave) {
        this.lastWave = lastWave;
    }

    public int getLastWave() {
        return lastWave;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
