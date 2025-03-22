package view.Animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Truck;

public class TruckAnimation extends Transition {
    private final Game game;
    private final Truck truck;
    private final double speed;
    private final int duration = 100;

    public TruckAnimation(Game game, Truck truck) {
        this.game = game;
        this.truck = truck;
        speed = truck.getSpeed();

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double x = truck.getX() + speed;
        if (truck.getX() < -50 && speed < 0) {
            truck.setX(game.WIDTH);
        } else if (truck.getX() > game.WIDTH && speed > 0) {
            truck.setX(-50);
        } else truck.setX(x);
    }
}
