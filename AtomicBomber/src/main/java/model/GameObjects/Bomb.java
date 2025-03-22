package model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animation.BombAnimation;

import java.util.Objects;

public class Bomb extends Rectangle {
    public final int WIDTH = 10;
    public final int HEIGHT = 10;
    private BombAnimation bombAnimation;

    public Bomb(Aircraft aircraft) {
        super(10, 10);
        setX(aircraft.getX() + aircraft.WIDTH/2);
        setY(aircraft.getY() + aircraft.HEIGHT/2 + 10);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Bomb.class.getResource("/Image/Objects/bomb.png")).toExternalForm())));
    }

    public Bomb() {
        super(10, 10);
        setY(0);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Bomb.class.getResource("/Image/Objects/bomb.png")).toExternalForm())));
    }

    public void setBombAnimation(BombAnimation bombAnimation) {
        this.bombAnimation = bombAnimation;
    }

    public BombAnimation getBombAnimation() {
        return bombAnimation;
    }
}
