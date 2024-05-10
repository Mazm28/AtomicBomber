package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    private Circle avatarCircle;
    @FXML
    private Label username;

    public void initialize(URL location, ResourceBundle resources) {
        User loggedInUser = User.getLoggedInUser();
        username.setText(loggedInUser.getUsername());
        String avatarPath = loggedInUser.getAvatarPath();
        Image avatar =new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream(avatarPath)));
        avatarCircle.setFill(new ImagePattern(avatar));
    }

    public void userLogout(ActionEvent actionEvent) throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/Main.fxml");
    }

    public void closeApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void openProfileMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/ProfileMenu.fxml");
    }

    public void openScoreBoard(ActionEvent actionEvent) {
    }

    public void openGameSetting(ActionEvent actionEvent) {
    }
}
