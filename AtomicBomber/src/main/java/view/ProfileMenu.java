package view;

import controller.ProfileMenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileMenu implements Initializable {
    final ProfileMenuController controller = new ProfileMenuController();
    @FXML
    private Label username;
    @FXML
    private Label wrongNewUsername;
    @FXML
    private Circle avatarCircle;
    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField newPassword;

    public void initialize(URL location, ResourceBundle resources) {
        User loggedInUser = User.getLoggedInUser();
        username.setText(loggedInUser.getUsername());
        String avatarPath = loggedInUser.getAvatarPath();
        Image avatar = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream(avatarPath)));
        avatarCircle.setFill(new ImagePattern(avatar));
    }

    public void changeUsername() {
        if (controller.changeUsername(newUsername.getText(), wrongNewUsername)) {
            username.setText(newUsername.getText());
        }
    }

    public void changePassword() {
        controller.changePassword(newPassword.getText());
    }

    public void openAvatarMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/AvatarMenu.fxml");
    }

    public void deleteAccount() throws IOException {
        controller.deleteAccount();
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/Main.fxml");
    }

    public void backToMainMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/MainMenu.fxml");
    }
}
