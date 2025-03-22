package model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.GameObjects.Aircraft;
import view.Animation.BombAnimation;
import view.Animation.NukeAnimation;

import java.util.Objects;

public class Nuclear extends Rectangle {
    public final int WIDTH = 40;
    public final int HEIGHT = 40;
    private NukeAnimation nukeAnimation;

    public Nuclear(Aircraft aircraft) {
        super(40, 40);
        setX(aircraft.getX() + aircraft.WIDTH/2);
        setY(aircraft.getY() + aircraft.HEIGHT/2 + 10);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(model.GameObjects.Bomb.class.getResource("/Image/Objects/nuke.png")).toExternalForm())));
    }

    public void setNukeAnimation(NukeAnimation nukeAnimation) {
        this.nukeAnimation = nukeAnimation;
    }

    public NukeAnimation getNukeAnimation() {
        return nukeAnimation;
    }
}