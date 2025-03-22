package view.Animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.Aircraft;
import model.GameObjects.NukeBonus;

public class NukeBonusAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final NukeBonus nukeBonus;
    private final double speed = -0.5;

    public NukeBonusAnimation(Game game, Pane pane, NukeBonus nukeBonus) {
        this.game = game;
        this.pane = pane;
        this.nukeBonus = nukeBonus;

        setCycleCount(-1);
        setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        nukeBonus.setY(nukeBonus.getY() + speed);
        Aircraft aircraft = game.getAircraft();
        if (aircraft.getBoundsInParent().intersects(nukeBonus.getBoundsInParent())) {
            if (!nukeBonus.isCatched()) {
                nukeBonus.setCatched(true);
                game.setNukeNumber(game.getNukeNumber() + 1);
                pane.getChildren().remove(nukeBonus);
            }
        }
        if (nukeBonus.getY() < 0) pane.getChildren().remove(nukeBonus);
    }
}
