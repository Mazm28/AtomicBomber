package view.Animation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Bomb;
import model.GameObjects.Nuclear;

public class NukeExplosionAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Nuclear nuclear;

    public NukeExplosionAnimation(Game game, Pane pane, Nuclear nuclear) {
        this.game = game;
        this.pane = pane;
        this.nuclear = nuclear;
        nuclear.setY(300);
        nuclear.setX(nuclear.getX() - 100);
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        int number = 0;
        if (0 <= v && v <= 0.33) number = 0;
        else if (0.33 < v && v <= 0.66) number = 1;
        else if (0.66 < v && v <= 1) number = 2;

        nuclear.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/explodingNuke" + number + ".png").toExternalForm())));
        nuclear.setWidth(200);
        nuclear.setHeight(200);
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(nuclear);
            }
        });
    }
}
