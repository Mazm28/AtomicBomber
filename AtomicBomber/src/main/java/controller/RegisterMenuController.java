package controller;

import javafx.scene.control.Label;
import model.User;

import java.util.Random;

public class RegisterMenuController {
    public boolean signUp(String username, String password, Label wrongSignUp) {
        if (username.isEmpty() || password.isEmpty()) {
            wrongSignUp.setText("Please enter a valid username or password");
        } else if (User.getUserByUsername(username) != null) {
            wrongSignUp.setText("Username is already taken");
        } else {
            Random random =new Random();
            int randomNumber = random.nextInt(5) + 1;
            String avatarPath = "/Image/Avatars/" + randomNumber + ".png";
            User user = new User(username, password, avatarPath);
            User.setLoggedInUser(user);
            return true;
        }
        return false;
    }
}
