package view.Animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;
import model.GameObjects.ClusterBonus;
import model.GameObjects.NukeBonus;

public class ClusterBonusAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final ClusterBonus clusterBonus;
    private final double speed = -0.5;

    public ClusterBonusAnimation(Game game, Pane pane, ClusterBonus clusterBonus) {
        this.game = game;
        this.pane = pane;
        this.clusterBonus = clusterBonus;

        setCycleCount(-1);
        setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        clusterBonus.setY(clusterBonus.getY() + speed);
        Aircraft aircraft = game.getAircraft();
        if (aircraft.getBoundsInParent().intersects(clusterBonus.getBoundsInParent())) {
            if (!clusterBonus.isCatched()) {
                clusterBonus.setCatched(true);
                game.setClusterNumber(game.getClusterNumber() + 1);
                pane.getChildren().remove(clusterBonus);
            }
        }
        if (clusterBonus.getY() < 0) pane.getChildren().remove(clusterBonus);
    }
}
