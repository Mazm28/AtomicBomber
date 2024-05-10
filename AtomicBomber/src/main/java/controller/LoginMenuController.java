package controller;

import javafx.scene.control.Label;
import model.User;

public class LoginMenuController {
    public boolean signIn(String username, String password, Label wrongLogin) {
        User user;
        if (username.isEmpty() || password.isEmpty()) {
            wrongLogin.setText("Please enter your username and password");
        } else if ((user = User.getUserByUsername(username)) == null) {
            wrongLogin.setText("There is no such user");
        } else if (!user.getPassword().equals(password)) {
            wrongLogin.setText("Wrong password");
        } else {
            wrongLogin.setText("Login successful");
            User.setLoggedInUser(user);
            return true;
        }
        return false;
    }
}
