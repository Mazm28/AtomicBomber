package view;

import controller.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;

public class LoginMenu {
    final LoginMenuController controller = new LoginMenuController();
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void loginUser() throws IOException {
        Launcher launcher = new Launcher();
        if (controller.signIn(username.getText(), password.getText(), wrongLogin)) {
            launcher.changeScene("/FXML/MainMenu.fxml");
        }
    }
}
