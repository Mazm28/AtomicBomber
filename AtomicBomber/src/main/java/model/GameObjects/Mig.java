package model.GameObjects;

import controller.ApplicationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game;
import view.Animation.MigAnimation;

import java.util.Objects;

public class Mig extends Rectangle {
    private final double WIDTH = 70;
    private final double HEIGHT = 30;
    private final double speed;
    private MigAnimation migAnimation;
    private boolean hit;
    private int radios;
    private boolean shoot = false;

    public Mig(int radios) {
        super(70, 30);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Mig.class.getResource("/Image/Objects/mig.png")).toExternalForm())));

        setX(1000);
        speed = -0.5;
        Game.getLastGame().getGameLauncher().makeMigAlert();
        this.setRotate(180);
        setY(200);
        this.radios = radios;
    }

    public void setMigAnimation(MigAnimation migAnimation) {
        this.migAnimation = migAnimation;
    }

    public MigAnimation getMigAnimation() {
        return migAnimation;
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
