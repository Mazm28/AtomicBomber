package model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animation.NukeBonusAnimation;

public class NukeBonus extends Rectangle {
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private NukeBonusAnimation nukeBonusAnimation;
    private boolean catched = false;

    public NukeBonus(Building building) {
        super(50, 50);
        setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/nukeBonus.png").toExternalForm())));
        setY(450);
        setX(building.getX());
    }

    public NukeBonusAnimation getNukeBonusAnimation() {
        return nukeBonusAnimation;
    }

    public void setNukeBonusAnimation(NukeBonusAnimation nukeBonusAnimation) {
        this.nukeBonusAnimation = nukeBonusAnimation;
    }

    public boolean isCatched() {
        return catched;
    }

    public void setCatched(boolean catched) {
        this.catched = catched;
    }
}
