package view.Animation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;

public class AircraftMovementAnimation extends Transition {
    private Pane pane;
    private Aircraft aircraft;
    private Game game;
    private static double speed = 1.0;

    public AircraftMovementAnimation(Pane pane, Game game, Aircraft aircraft) {
        this.pane = pane;
        this.game = game;
        this.aircraft = aircraft;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        double deltaX = speed * Math.cos(Math.toRadians(aircraft.getRotate()));
        double deltaY = speed * Math.sin(Math.toRadians(aircraft.getRotate()));
        if (aircraft.getX() < 0 - aircraft.WIDTH) {
            aircraft.setX(game.WIDTH);
        } else if (aircraft.getX() > game.WIDTH) {
            aircraft.setX(-aircraft.WIDTH);
        }

        int angle = (int) (aircraft.getRotate() % 360);
        if (angle < 0) {
            angle += 360;
        }
        if (aircraft.getY() <= 0) {
            aircraft.setY(0);
            if (angle >= 270 || angle == 0) {
                aircraft.setRotate(0);
            } else aircraft.setRotate(180);
        } else if (aircraft.getY() >= 450) {
            this.stop();
            game.setHp(game.getHp() - 1);
            AircraftExplosionAnimation aircraftExplosionAnimation = new AircraftExplosionAnimation(aircraft, pane, game);
            game.addToAnimation(aircraftExplosionAnimation);
            aircraftExplosionAnimation.play();
        }
        aircraft.setX(aircraft.getX() + deltaX);
        aircraft.setY(aircraft.getY() + deltaY);
    }
}
