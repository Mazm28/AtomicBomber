package view;

import javafx.event.ActionEvent;
import model.User;

import java.io.IOException;

public class SettingMenu {
    public void changeKeys(ActionEvent actionEvent) {
        User.getLoggedInUser().changeKeys();
    }

    public void changeColors(ActionEvent actionEvent) {
        User.getLoggedInUser().changeColors();
    }

    public void changeSongSituation(ActionEvent actionEvent) {
        User.getLoggedInUser().setMute(!User.getLoggedInUser().isMute());
    }

    public void changeToEasy() {
    }

    public void changeToMedium() {
    }

    public void changeToHard() {
    }

    public void backToMainMenu() throws IOException {
        Launcher launcher = new Launcher();
        launcher.changeScene("/FXML/MainMenu.fxml");
    }
}
