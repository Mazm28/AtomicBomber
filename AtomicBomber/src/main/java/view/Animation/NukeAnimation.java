package view.Animation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.*;

public class NukeAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Nuclear nuclear;
    private final double acceleration = 0.01;
    private double vSpeed = 0;
    private final int duration = 100;

    public NukeAnimation(Pane pane, Game game, Nuclear nuclear) {
        this.pane = pane;
        this.game = game;
        this.nuclear = nuclear;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double y = nuclear.getY() + vSpeed;
        vSpeed += acceleration;

        for (Node child : game.getTrucks().getChildren()) {
            Truck truck = (Truck) child;
            if (Math.abs(truck.getX() - nuclear.getX()) < 100 && nuclear.getY() >= 450) {
                Truck.explodeTruck(truck, game, pane);
            }
        }

        for (Node child : game.trees.getChildren()) {
            Tree tree = (Tree) child;
            if (Math.abs(tree.getX() - nuclear.getX()) < 100 && nuclear.getY() >= 450) {
                if (tree.isHit()) continue;
                tree.setHit(true);

                TreeExplosionAnimation treeExplosionAnimation = new TreeExplosionAnimation(tree, pane, game.trees, game);
                game.addToAnimation(treeExplosionAnimation);
                treeExplosionAnimation.play();
            }
        }

        for (Node child : game.tanks.getChildren()) {
            Tank tank = (Tank) child;
            if (Math.abs(tank.getX() - nuclear.getX()) < 100 && nuclear.getY() >= 450) {
                Tank.explodeTank(tank, game, pane);
            }
        }

        Building building = game.getBuilding();
        if (Math.abs(building.getX() - nuclear.getX()) < 100 && nuclear.getY() >= 450) {
            Building.explodeBuilding(building, game, pane);
        }

        Trench trench = game.getTrench();
        if (Math.abs(trench.getX() - nuclear.getX()) < 100 && nuclear.getY() >= 450) {
            Trench.explodeTrench(trench, game, pane);
        }

        if (nuclear.getY() >= 450) {
            this.stop();
            NukeExplosionAnimation nukeExplosionAnimation = new NukeExplosionAnimation(game, pane, nuclear);
            game.addToAnimation(nukeExplosionAnimation);
            nukeExplosionAnimation.play();
            return;
        }
        nuclear.setY(y);
    }
}