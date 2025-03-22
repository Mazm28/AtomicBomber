package model.GameObjects;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animation.ClusterBonusAnimation;
import view.Animation.TrenchExplosionAnimation;

import java.util.Objects;

public class Trench extends Rectangle {
    private final double WIDTH = 70;
    private final double HEIGHT = 60;
    private boolean hit = false;

    public Trench() {
        super(70, 60);
        int x = ApplicationController.getRandom().nextInt(700) + 20;
        setX(x);
        setY(450);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Building.class.getResource("/Image/Objects/trench.png")).toExternalForm())));
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public static void makeClusterBonus(Game game, Pane pane, Trench trench) {
        ClusterBonus clusterBonus = new ClusterBonus(trench);
        clusterBonus.setClusterBonusAnimation(new ClusterBonusAnimation(game, pane, clusterBonus));
        pane.getChildren().add(clusterBonus);
        clusterBonus.getClusterBonusAnimation().play();
    }

    public static void explodeTrench(Trench trench, Game game, Pane pane) {
        if (!trench.isHit()) {
            trench.setHit(true);

            Trench.makeClusterBonus(game, pane, trench);

            game.addScore(30);
            game.setKills(game.getKills() + 2);

            TrenchExplosionAnimation trenchExplosionAnimation = new TrenchExplosionAnimation(pane, game, trench);
            game.addToAnimation(trenchExplosionAnimation);
            trenchExplosionAnimation.play();
        }
    }
}
