package model.GameObjects;

import controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animation.BuildingExplosionAnimation;
import view.Animation.NukeBonusAnimation;

import java.util.Objects;

public class Building extends Rectangle {
    private final double HEIGHT = 100;
    private final double WIDTH = 90;
    private boolean hit = false;

    public Building() {
        super(100, 90);
        int x = ApplicationController.getRandom().nextInt(700) + 20;
        setX(x);
        setY(450);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Building.class.getResource("/Image/Objects/building.png")).toExternalForm())));
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public static void makeNukeBonus(Game game, Pane pane, Building building) {
        NukeBonus nukeBonus = new NukeBonus(building);
        nukeBonus.setNukeBonusAnimation(new NukeBonusAnimation(game, pane, nukeBonus));
        pane.getChildren().add(nukeBonus);
        nukeBonus.getNukeBonusAnimation().play();
    }

    public static void explodeBuilding(Building building, Game game, Pane pane) {
        if (!building.isHit()) {
            building.setHit(true);

            Building.makeNukeBonus(game, pane, building);

            game.addScore(50);
            game.setKills(game.getKills() + 3);

            BuildingExplosionAnimation buildingExplosionAnimation = new BuildingExplosionAnimation(pane, game, building);
            game.addToAnimation(buildingExplosionAnimation);
            buildingExplosionAnimation.play();
        }
    }
}
