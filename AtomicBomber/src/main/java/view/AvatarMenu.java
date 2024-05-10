package view;

import controller.AvatarMenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AvatarMenu implements Initializable {
    final AvatarMenuController controller = new AvatarMenuController();
    @FXML
    private Circle mainCircle;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle5;
    private static String newAvatarPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User loggedInUser = User.getLoggedInUser();
        String avatarPath = loggedInUser.getAvatarPath();
        newAvatarPath = avatarPath;
        Image avatar = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream(avatarPath)));
        mainCircle.setFill(new ImagePattern(avatar));
        Image avatar1 = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream("/Image/Avatars/1.png")));
        circle1.setFill(new ImagePattern(avatar1));
        Image avatar2 = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream("/Image/Avatars/2.png")));
        circle2.setFill(new ImagePattern(avatar2));
        Image avatar3 = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream("/Image/Avatars/3.png")));
        circle3.setFill(new ImagePattern(avatar3));
        Image avatar4 = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream("/Image/Avatars/4.png")));
        circle4.setFill(new ImagePattern(avatar4));
        Image avatar5 = new Image(Objects.requireNonNull(MainMenu.class.getResourceAsStream("/Image/Avatars/5.png")));
        circle5.setFill(new ImagePattern(avatar5));
    }

    public void chooseAvatar(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getTarget();
        controller.chooseAvatar(circle, mainCircle);
    }

    public static void setNewAvatarPath(String newAvatarPath) {
        AvatarMenu.newAvatarPath = newAvatarPath;
    }

    public void changeAvatar() {
        User loggedInUser = User.getLoggedInUser();
        loggedInUser.setAvatarPath(newAvatarPath);
    }

    public void backToProfileMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/ProfileMenu.fxml");
    }

    public void uploadAvatar(MouseEvent mouseEvent) throws IOException {
        controller.chooseAvatarFromFiles(mainCircle, (Button) mouseEvent.getSource());
    }
}
