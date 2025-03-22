package model.GameObjects;

import controller.ApplicationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game;
import view.Animation.TankAnimation;
import view.Animation.TankExplosionAnimation;

import java.util.Objects;

public class Tank extends Rectangle {
    private final double WIDTH = 70;
    private final double HEIGHT = 30;
    private final double speed;
    private TankAnimation tankAnimation;
    private boolean hit;
    private int radios;
    private boolean shoot = false;

    public Tank() {
        super(70, 30);
        int x = ApplicationController.getRandom().nextInt(2) + 1;
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Truck.class.getResource("/Image/Objects/tank.png")).toExternalForm())));
        if (x == 1) {
            setX(805);
            speed = -0.4;
            this.setRotate(180);
        } else {
            setX(-50);
            speed = 0.4;
        }
        setY(450);
    }

    public Tank(int radios) {
        super(70, 30);
        int x = ApplicationController.getRandom().nextInt(2) + 1;
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Truck.class.getResource("/Image/Objects/tank.png")).toExternalForm())));
        if (x == 1) {
            setX(805);
            speed = -0.4;
            this.setRotate(180);
        } else {
            setX(-50);
            speed = 0.4;
        }
        setY(450);
        this.radios = radios;
    }

    public void setTankAnimation(TankAnimation tankAnimation) {
        this.tankAnimation = tankAnimation;
    }

    public TankAnimation getTankAnimation() {
        return tankAnimation;
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

    public static void explodeTank(Tank tank, Game game, Pane pane) {
        if (tank.isHit()) return;
        tank.setHit(true);

        game.addScore(200);
        game.setKills(game.getKills() + 1);

        TankExplosionAnimation tankExplosionAnimation = new TankExplosionAnimation(tank, pane, game.trees, game);
        game.addToAnimation(tankExplosionAnimation);
        tankExplosionAnimation.play();
    }

    public int getRadios() {
        return radios;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void shoot() {
        setShoot(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
            setShoot(false);
        }));
        timeline.play();
    }
}
