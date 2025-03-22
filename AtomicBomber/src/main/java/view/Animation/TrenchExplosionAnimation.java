package view.Animation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Building;
import model.GameObjects.Trench;

public class TrenchExplosionAnimation extends Transition {
    private final Pane pane;
    private final Game game;
    private final Trench trench;

    public TrenchExplosionAnimation(Pane pane, Game game, Trench trench) {
        this.trench = trench;
        this.game = game;
        this.pane = pane;
        setCycleCount(1);
        setCycleDuration(Duration.millis(100));
    }
    @Override
    protected void interpolate(double v) {
        int number = 0;
        if (0 <= v && v <= 0.33) number = 0;
        else if (0.33 < v && v <= 0.66) number = 1;
        else if (0.66 < v && v <= 1) number = 2;

        trench.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/burningTrench" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(trench);
            }
        });
    }
}
