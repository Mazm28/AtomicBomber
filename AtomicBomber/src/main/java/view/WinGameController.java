package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Game;
import model.User;

import java.io.IOException;

public class WinGameController {
    @FXML
    private Label won;
    @FXML
    private Label kills;
    @FXML
    private Label accuracy;
    private final Game game = Game.getLastGame();

    @FXML
    private void initialize() {
        User user = User.getLoggedInUser();
        if (game.getScore() > user.getHighestScore()) {
            user.setHighestScore(game.getScore());
            user.setMostKills(game.getKills());
            user.setLastWave(game.getWave());
        }
        if (game.isWon()) {
            won.setText("You Won!");
        } else won.setText("You Lost!");
        accuracy.setText("Accuracy: " + (int) ((double) game.getKills() / game.getTotalBombShot() * 100) + "%");
        kills.setText("Kills: " + game.getKills());
    }

    public void backToMainMenu() throws IOException {
        WinMenu.getMediaPlayer().pause();
        WinMenu.getWinStage().hide();
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/MainMenu.fxml");
    }
}
