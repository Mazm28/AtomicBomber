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
import model.GameObjects.Tank;
import model.GameObjects.Truck;

public class TankExplosionAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Tank tank;
    private final Group tanks;

    public TankExplosionAnimation(Tank tank, Pane pane, Group tanks, Game game) {
        this.tanks = tanks;
        this.pane = pane;
        this.game = game;
        this.tank = tank;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        int number = 0;
        if (0 <= v && v <= 0.33) number = 0;
        else if (0.33 < v && v <= 0.66) number = 1;
        else if (0.66 < v && v <= 1) number = 2;

        tank.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/burningTank" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.tanks.getChildren().remove(tank);
                pane.getChildren().remove(tank);
            }
        });
    }
}
