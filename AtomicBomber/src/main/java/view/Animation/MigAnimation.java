package view.Animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;
import model.GameObjects.Mig;
import model.GameObjects.Missile;
import model.GameObjects.Tank;

public class MigAnimation extends Transition {
    private final Pane pane;
    private final Game game;
    private final Mig mig;
    private final double speed;
    private final int duration = 100;

    public MigAnimation(Pane pane, Game game, Mig mig) {
        this.pane = pane;
        this.game = game;
        this.mig = mig;
        speed = mig.getSpeed();

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double x = mig.getX() + speed;
        if (mig.getX() < -50 && speed < 0) {
            pane.getChildren().remove(mig);
        } else if (mig.getX() > game.WIDTH && speed > 0) {
            pane.getChildren().remove(mig);
        } else mig.setX(x);


        Aircraft aircraft = game.getAircraft();
        if (Math.sqrt(Math.pow(aircraft.getX() - mig.getX(), 2) + Math.pow(aircraft.getY() - mig.getY(), 2)) < mig.getRadios() && !mig.isShoot()) {
            mig.shoot();
            Missile missile = new Missile(mig);
            pane.getChildren().add(missile);
            MissileAnimation missileAnimation = new MissileAnimation(pane, missile, game, aircraft, mig);
            missile.setMissileAnimation(missileAnimation);
            missileAnimation.play();
        }
    }
}
