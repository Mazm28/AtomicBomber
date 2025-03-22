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
import model.GameObjects.Truck;

public class TruckExplosionAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Truck truck;
    private final Group trucks;

    public TruckExplosionAnimation(Truck truck, Pane pane, Group trucks, Game game) {
        this.trucks = trucks;
        this.pane = pane;
        this.game = game;
        this.truck = truck;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        int number = 0;
        if (0 <= v && v <= 0.33) number = 0;
        else if (0.33 < v && v <= 0.66) number = 1;
        else if (0.66 < v && v <= 1) number = 2;

        truck.setFill(new ImagePattern(new Image(getClass().getResource("/Image/Objects/burningTruck" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.getTrucks().getChildren().remove(truck);
                pane.getChildren().remove(truck);
            }
        });
    }
}
