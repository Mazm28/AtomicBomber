package view.Animation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.*;

public class BombAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Bomb bomb;
    private final double acceleration = 0.04;
    private double vSpeed = 0;
    private double hSpeed;
    private final int duration = 100;

    public BombAnimation(Pane pane, Game game, Bomb bomb, boolean angle) {
        this.pane = pane;
        this.game = game;
        this.bomb = bomb;
        if (angle) {
            hSpeed = -2;
        } else hSpeed = 2;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    public BombAnimation(Pane pane, Game game, Bomb bomb, int type) {
        this.pane = pane;
        this.game = game;
        this.bomb = bomb;
        this.hSpeed = 0;
        bomb.setX(type * 200);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double y = bomb.getY() + vSpeed;
        double x = bomb.getX() + hSpeed;
        vSpeed += acceleration;

        for (Node child : game.getTrucks().getChildren()) {
            Truck truck = (Truck) child;
            if (truck.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
                Truck.explodeTruck(truck, game, pane);
            }
        }

        for (Node child : game.trees.getChildren()) {
            Tree tree = (Tree) child;
            if (tree.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
                if (tree.isHit()) continue;
                tree.setHit(true);

                TreeExplosionAnimation treeExplosionAnimation = new TreeExplosionAnimation(tree, pane, game.trees, game);
                game.addToAnimation(treeExplosionAnimation);
                treeExplosionAnimation.play();
            }
        }

        for (Node child : game.tanks.getChildren()) {
            Tank tank = (Tank) child;
            if (tank.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
                Tank.explodeTank(tank, game, pane);
            }
        }

        Building building = game.getBuilding();
        if (building.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
            Building.explodeBuilding(building, game, pane);
        }

        Trench trench = game.getTrench();
        if (trench.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
            Trench.explodeTrench(trench, game, pane);
        }

        if (bomb.getY() >= 500) {
            this.stop();
            BombExplosionAnimation bombExplosionAnimation = new BombExplosionAnimation(game, pane, bomb);
            game.addToAnimation(bombExplosionAnimation);
            bombExplosionAnimation.play();
            return;
        }
        bomb.setX(x);
        bomb.setY(y);
    }
}
