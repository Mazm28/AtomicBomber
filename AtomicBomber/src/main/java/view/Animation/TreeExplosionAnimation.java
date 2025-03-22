package view.Animation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Tree;

public class TreeExplosionAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Tree tree;
    private final Group trees;

    public TreeExplosionAnimation(Tree tree, Pane pane,Group trees, Game game) {
        this.tree = tree;
        this.pane = pane;
        this.game = game;
        this.trees = trees;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        int number = 0;
        if (0 <= v && v <= 0.33) number = 0;
        else if (0.33 < v && v <= 0.66) number = 1;
        else if (0.66 < v && v <= 1) number = 2;

        tree.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/burningTree" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.trees.getChildren().remove(tree);
                pane.getChildren().remove(tree);
            }
        });
    }
}
