package model.GameObjects;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animation.TruckAnimation;
import view.Animation.TruckExplosionAnimation;

import java.util.Objects;

public class Truck extends Rectangle {
    private final double WIDTH = 70;
    private final double HEIGHT = 30;
    private final double speed;
    private TruckAnimation truckAnimation;
    private boolean hit = false;

    public Truck() {
        super(70, 30);
        int x = ApplicationController.getRandom().nextInt(2) + 1;
        if (x == 1) {
            setX(805);
            speed = -0.5;
        } else {
            setX(-50);
            speed = 0.5;
        }
        setY(450);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Truck.class.getResource("/Image/Objects/truck.png")).toExternalForm())));
    }

    public void setTruckAnimation(TruckAnimation truckAnimation) {
        this.truckAnimation = truckAnimation;
    }

    public TruckAnimation getTruckAnimation() {
        return truckAnimation;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public static void explodeTruck(Truck truck, Game game, Pane pane) {
        if (truck.isHit()) return;
        truck.setHit(true);

        game.addScore(100);
        game.setKills(game.getKills() + 1);
        truck.getTruckAnimation().pause();

        TruckExplosionAnimation truckExplosionAnimation = new TruckExplosionAnimation(truck, pane, game.trees, game);
        game.addToAnimation(truckExplosionAnimation);
        truckExplosionAnimation.play();
    }
}
