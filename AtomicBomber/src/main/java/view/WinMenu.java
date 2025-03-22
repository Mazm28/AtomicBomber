package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.User;

import java.net.URL;
import java.util.Objects;

public class WinMenu extends Application {
    private static MediaPlayer mediaPlayer;
    private static final Stage winStage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        stage.hide();
        winStage.centerOnScreen();
        URL url = Launcher.class.getResource("/FXML/WinMenu.fxml");
        assert url != null;
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        winStage.getIcons().add(new Image(Objects.requireNonNull(GameLauncher.class.getResourceAsStream("/Image/Icon/main.png"))));
        winStage.setScene(scene);
        winStage.show();

        Media media = new Media(Objects.requireNonNull(getClass().getResource("/Music/3.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }

    public static Stage getWinStage() {
        return winStage;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
