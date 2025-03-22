package model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animation.MissileAnimation;

public class Missile extends Rectangle {
    private int WIDTH = 5;
    private int HEIGHT = 5;
    private MissileAnimation missileAnimation;
    private boolean hit;

    public Missile(Tank tank) {
        super(5, 5);
        setX(tank.getX());
        setY(tank.getY());
        setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/bomb.png").toExternalForm())));
    }

    public Missile(Mig mig) {
        super(5, 5);
        setX(mig.getX());
        setY(mig.getY());
        setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/bomb.png").toExternalForm())));
    }

    public void setMissileAnimation(MissileAnimation missileAnimation) {
        this.missileAnimation = missileAnimation;
    }

    public MissileAnimation getMissileAnimation() {
        return missileAnimation;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }
}
