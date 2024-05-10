package controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import view.AvatarMenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class AvatarMenuController {
    public void chooseAvatar(Circle circle, Circle mainCircle) {
        String selectedAvatar = circle.idProperty().getValue();
        int number = Integer.parseInt(String.valueOf(selectedAvatar.charAt(6)));
        String newAvatarPath = "/Image/Avatars/" + number + ".png";
        AvatarMenu.setNewAvatarPath(newAvatarPath);
        Image newAvatarImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(newAvatarPath)));
        mainCircle.setFill(new ImagePattern(newAvatarImage));
    }

    public void chooseAvatarFromFiles(Circle mainCircle, Button button) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        fileChooser.setInitialDirectory(new java.io.File("src/main/resources/Image/Avatars"));
        File selectedFile = fileChooser.showOpenDialog(button.getScene().getWindow());
        if (selectedFile != null) {
            Image newAvatarImage = new Image(selectedFile.toURI().toString());
            mainCircle.setFill(new ImagePattern(newAvatarImage));
            String path = "src/main/resources/Image/Avatars/";
            Path destination = (Path) java.nio.file.Path.of(path + selectedFile.getName());
            Files.copy(selectedFile.toPath(), (java.nio.file.Path) destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
