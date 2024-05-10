package controller;

import javafx.scene.control.Label;
import model.User;

public class ProfileMenuController {
    public boolean changeUsername(String newUsername, Label wrongNewUsername) {
        User loggedInUser = User.getLoggedInUser();
        if (newUsername.isEmpty()) {
            wrongNewUsername.setText("Please Enter A Valid Username!");
        } else if (loggedInUser.getUsername().equals(newUsername)) {
            wrongNewUsername.setText("Please Enter A New Username!");
        } else if (User.getUserByUsername(newUsername) != null) {
            wrongNewUsername.setText("Username Is Already Taken!");
        } else {
            loggedInUser.setUsername(newUsername);
            return true;
        }
        return false;
    }

    public void changePassword(String newPassword) {
        User.getLoggedInUser().setPassword(newPassword);
    }

    public void deleteAccount() {
        User.removeUser(User.getLoggedInUser());
    }
}
