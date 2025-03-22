package view.Animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;
import model.GameObjects.Mig;
import model.GameObjects.Missile;
import model.GameObjects.Tank;

public class MissileAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Missile missile;
    private final Aircraft aircraft;
    private final double speed = 0.5;
    private double angle;

    public MissileAnimation(Pane pane, Missile missile, Game game, Aircraft aircraft, Tank tank) {
        this.game = game;
        this.pane = pane;
        this.missile = missile;
        this.aircraft = aircraft;

        angle = Math.atan((tank.getY() - aircraft.getY()) / (tank.getX() - aircraft.getX()));
        if (angle > 0) {
            angle -= Math.PI;
        }
        missile.setRotate(Math.toDegrees(angle) + 180);

        System.out.println(angle);

        setCycleCount(-1);
        setCycleDuration(Duration.millis(100));
    }

    public MissileAnimation(Pane pane, Missile missile, Game game, Aircraft aircraft, Mig mig) {
        this.game = game;
        this.pane = pane;
        this.missile = missile;
        this.aircraft = aircraft;

        angle = Math.atan((mig.getY() - aircraft.getY()) / (mig.getX() - aircraft.getX()));
        if (angle > 0) {
            angle -= Math.PI;
        }
        missile.setRotate(Math.toDegrees(angle) + 180);

        System.out.println(angle);

        setCycleCount(-1);
        setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        missile.setX(missile.getX() + speed * Math.sin(angle));
        missile.setY(missile.getY() + speed * Math.cos(angle));

        if (missile.getY() <= 0) pane.getChildren().remove(missile);
        if (missile.getY() >= pane.getHeight()) pane.getChildren().remove(missile);

        Aircraft aircraft = game.getAircraft();
        if (Math.abs(aircraft.getY() - missile.getY()) < 40 && Math.abs(aircraft.getX() - missile.getX()) < 40) {
            if (!missile.isHit()) {
                missile.setHit(true);
                pane.getChildren().remove(missile);
                aircraft.getAircraftTransition().stop();
                game.setHp(game.getHp() - 1);
                AircraftExplosionAnimation aircraftExplosionAnimation = new AircraftExplosionAnimation(aircraft, pane, game);
                game.addToAnimation(aircraftExplosionAnimation);
                aircraftExplosionAnimation.play();
            }
        }
    }
}
