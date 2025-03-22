package model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Animation.ClusterBonusAnimation;
import view.Animation.NukeBonusAnimation;

public class ClusterBonus extends Rectangle {
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private ClusterBonusAnimation clusterBonusAnimation;
    private boolean catched = false;

    public ClusterBonus(Trench trench) {
        super(50, 50);
        setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/clusterBonus.png").toExternalForm())));
        setY(450);
        setX(trench.getX());
    }


    public ClusterBonusAnimation getClusterBonusAnimation() {
        return clusterBonusAnimation;
    }

    public void setClusterBonusAnimation(ClusterBonusAnimation clusterBonusAnimation) {
        this.clusterBonusAnimation = clusterBonusAnimation;
    }

    public boolean isCatched() {
        return catched;
    }

    public void setCatched(boolean catched) {
        this.catched = catched;
    }
}
