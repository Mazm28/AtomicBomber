package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    public void userLogout() throws Exception {
        Launcher launcher = new Launcher();
        launcher.start(Launcher.stage);
        launcher.changeScene("/FXML/Main.fxml");
    }

    public void closeApp() {
        Platform.exit();
    }

    public void openProfileMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/ProfileMenu.fxml");
    }

    public void openScoreBoard() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/ScoreBoard.fxml");
    }

    public void openGameSetting() {
    }

    public void startNewGame() throws Exception {
        Stage stage = Launcher.stage;
        new GameLauncher(User.getLoggedInUser()).start(stage);
    }
}
