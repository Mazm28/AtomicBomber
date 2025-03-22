package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ScoreBoard implements Initializable {
    @FXML
    private Label no1;
    @FXML
    private Label no2;
    @FXML
    private Label no3;
    @FXML
    private Label no4;
    @FXML
    private Label no5;
    @FXML
    private Label no6;
    @FXML
    private Label no7;
    @FXML
    private Label no8;
    @FXML
    private Label no9;
    @FXML
    private Label no10;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<User> users = User.getUsers();
        Collections.sort(users, new Comparator<User>() {
            public int compare(User o1, User o2) {
                if (o1.getHighestScore() != o2.getHighestScore()) {
                    return Integer.compare(o2.getHighestScore(), o1.getHighestScore());
                } else {
                    return Integer.compare(o2.getMostKills(), o1.getMostKills());
                }
            }
        });

        if (!users.isEmpty() && users.getFirst().getHighestScore() >= 0) {
            no1.setText(users.getFirst().getUsername() + " " +
                    users.getFirst().getHighestScore() + " " + users.getFirst().getMostKills() + " " + users.getFirst().getLastWave());
        }
        if (users.size() > 1 && users.get(1).getHighestScore() >= 0) {
            no2.setText(users.get(1).getUsername() + " " +
                    users.get(1).getHighestScore() + " " + users.get(1).getMostKills() + " " + users.get(1).getLastWave());
        }
        if (users.size() > 2 && users.get(2).getHighestScore() >= 0) {
            no3.setText(users.get(2).getUsername() + " " +
                    users.get(2).getHighestScore() + " " + users.get(2).getMostKills() + " " + users.get(2).getLastWave());
        }
        if (users.size() > 3 && users.get(3).getHighestScore() >= 0) {
            no4.setText(users.get(3).getUsername() + " " +
                    users.get(3).getHighestScore() + " " + users.get(3).getMostKills() + " " + users.get(3).getLastWave());
        }
        if (users.size() > 4 && users.get(4).getHighestScore() >= 0) {
            no5.setText(users.get(4).getUsername() + " " +
                    users.get(4).getHighestScore() + " " + users.get(4).getMostKills() + " " + users.get(4).getLastWave());
        }
        if (users.size() > 5 && users.get(5).getHighestScore() >= 0) {
            no6.setText(users.get(5).getUsername() + " " +
                    users.get(5).getHighestScore() + " " + users.get(5).getMostKills() + " " + users.get(5).getLastWave());
        }
        if (users.size() > 6 && users.get(6).getHighestScore() >= 0) {
            no7.setText(users.get(6).getUsername() + " " +
                    users.get(6).getHighestScore() + " " + users.get(6).getMostKills() + " " + users.get(6).getLastWave());
        }
        if (users.size() > 7 && users.get(7).getHighestScore() >= 0) {
            no8.setText(users.get(7).getUsername() + " " +
                    users.get(7).getHighestScore() + " " + users.get(7).getMostKills() + " " + users.get(7).getLastWave());
        }
        if (users.size() > 8 && users.get(8).getHighestScore() >= 0) {
            no9.setText(users.get(8).getUsername() + " " +
                    users.get(8).getHighestScore() + " " + users.get(8).getMostKills() + " " + users.get(8).getLastWave());
        }
        if (users.size() > 9 && users.get(9).getHighestScore() >= 0) {
            no10.setText(users.get(9).getUsername() + " " +
                    users.get(9).getHighestScore() + " " + users.get(9).getMostKills() + " " + users.get(9).getLastWave());
        }
    }

    public void backToMainMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/MainMenu.fxml");
    }
}
