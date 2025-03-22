package view.Animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;
import model.GameObjects.Building;
import model.GameObjects.Missile;
import model.GameObjects.Tank;

public class TankAnimation  extends Transition {
    private final Pane pane;
    private final Game game;
    private final Tank tank;
    private final double speed;
    private final int duration = 100;
    private final boolean shooter;

    public TankAnimation(Pane pane, Game game, Tank tank, boolean shooter) {
        this.pane = pane;
        this.game = game;
        this.tank = tank;
        speed = tank.getSpeed();
        this.shooter = shooter;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double x = tank.getX() + speed;
        if (tank.getX() < -50 && speed < 0) {
            tank.setX(game.WIDTH);
        } else if (tank.getX() > game.WIDTH && speed > 0) {
            tank.setX(-50);
        } else tank.setX(x);

        if (shooter && !tank.isHit()) {
            Aircraft aircraft = game.getAircraft();
            if (Math.sqrt(Math.pow(aircraft.getX() - tank.getX(), 2) + Math.pow(aircraft.getY() - tank.getY(), 2)) < tank.getRadios() && !tank.isShoot()) {
                tank.shoot();
                Missile missile = new Missile(tank);
                pane.getChildren().add(missile);
                MissileAnimation missileAnimation = new MissileAnimation(pane, missile, game, aircraft, tank);
                missile.setMissileAnimation(missileAnimation);
                missileAnimation.play();
            }
        }
    }
}
