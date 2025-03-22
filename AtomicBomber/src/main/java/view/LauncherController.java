package view;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class LauncherController implements Initializable {
    @FXML
    private VBox vBox;
    private Parent fxml;

    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vBox);
        t.setToX(vBox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException ignored) {

        }
        });
    }

    @FXML
    private void openSignIn(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vBox);
        t.setToX(vBox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(LauncherController.class.getResource("/FXML/SignIn.fxml")));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(fxml);
            } catch (IOException ignored) {

            }
        });
    }

    @FXML
    private void openSignUp(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vBox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/SignUp.fxml")));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(fxml);
            } catch (IOException ignored) {

            }
        });
    }

    @FXML
    private void playAsGuest(ActionEvent event) throws Exception {
        Stage stage = Launcher.stage;
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        String avatarPath = "/Image/Avatars/" + randomNumber + ".png";
        User user = new User("Guest", null, avatarPath);
        User.setLoggedInUser(user);
        new GameLauncher(user).start(stage);
    }
}
