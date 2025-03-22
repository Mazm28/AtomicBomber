package model.GameObjects;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tree extends Rectangle {
    private final double WIDTH = 30;
    private final double HEIGHT = 60;
    private boolean hit = false;

    public Tree() {
        super(30, 60);
        int x = ApplicationController.getRandom().nextInt(700) + 20;
        setX(x);
        setY(450);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Building.class.getResource("/Image/Objects/tree.png")).toExternalForm())));
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
