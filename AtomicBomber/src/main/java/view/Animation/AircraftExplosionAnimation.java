package view.Animation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;
import model.GameObjects.Tank;

public class AircraftExplosionAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Aircraft aircraft;

    public AircraftExplosionAnimation(Aircraft aircraft, Pane pane, Game game) {
        this.aircraft = aircraft;
        this.pane = pane;
        this.game = game;

        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        int number = 0;
        if (0 <= v && v <= 0.33) number = 0;
        else if (0.33 < v && v <= 0.66) number = 1;
        else if (0.66 < v && v <= 1) number = 2;

        aircraft.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/burningAircraft" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (game.getHp() <= 0) {
                    try {
                        game.looseGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else reCreateAirCraft();
            }
        });
    }

    private void reCreateAirCraft() {
        aircraft.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/aircraft.png").toString())));
        aircraft.setRotate(0);
        aircraft.setX(20);
        aircraft.setY(game.HEIGHT/2 - 100);
        aircraft.getAircraftTransition().play();
    }
}
