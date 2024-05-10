package view;

import controller.RegisterMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterMenu {
    final RegisterMenuController controller = new RegisterMenuController();
    @FXML
    private Label wrongSingIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void registerUser() throws IOException {
        Launcher launcher = new Launcher();
        if (controller.signUp(username.getText(), password.getText(), wrongSingIn)) {
            launcher.changeScene("/FXML/MainMenu.fxml");
        }
    }
}
