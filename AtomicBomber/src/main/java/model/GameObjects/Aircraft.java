package model.GameObjects;

import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.User;

import java.util.Objects;

public class Aircraft extends Rectangle {
    public final double WIDTH = 75;
    public final double HEIGHT = 30;
    public final Game game;
    private Transition aircraftTransition;

    public Aircraft(Game game) {
        super(100, 100);
        this.game = game;
        setX(20);
        setY(game.HEIGHT/2 - 100);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Aircraft.class.getResource("/Image/Objects/aircraft.png")).toExternalForm())));
        if (User.getLoggedInUser().getGameSetting().isBlackAndWhite()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1.0);
            this.setEffect(colorAdjust);
        }
    }

    public void setAircraftTransition(Transition aircraftTransition) {
        this.aircraftTransition = aircraftTransition;
    }

    public Transition getAircraftTransition() {
        return aircraftTransition;
    }
}
