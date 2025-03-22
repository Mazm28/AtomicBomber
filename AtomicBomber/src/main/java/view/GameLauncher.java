package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Game;
import model.GameObjects.*;
import model.GameSetting;
import model.User;
import view.Animation.*;

import java.util.Objects;

public class GameLauncher extends Application {
    private MediaPlayer mediaPlayer;
    private GameSetting gameSetting;
    private Label waveLabel = new Label();
    private Label kills = new Label();
    private Label scores = new Label();
    private Label nukes = new Label();
    private Label clusters = new Label();
    private Label migAlert = new Label();
    public KeyCode up;
    public KeyCode down;
    public KeyCode left;
    public KeyCode right;
    public KeyCode bombing;
    public int aircraftAngle = 0;
    public static Stage gameStage = new Stage();
    public final Game game;
    public Pane pane;
    public Aircraft aircraft;
    public Bomb bomb;
    public Nuclear nuclear;
    public Building building;
    public Trench trench;
    private Timeline createTrucks;
    private Timeline createTanks;
    private Timeline checkWave;
    private Timeline createMigs;

    GameLauncher(User user) {
        game = new Game(user, this);
        Game.setLastGame(game);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/Music/1.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();

        setGameSetting();
        stage.hide();
        createPane();

        createBuilding();
        createTrench();
        createAircraft();
        pane.getChildren().add(game.getAircraft());
        pane.getChildren().add(game.getTrucks());
        pane.getChildren().add(game.tanks);
        createObjects();
        pane.getChildren().add(game.trees);
        createTrees();

        setLabels();
        Timeline updateLabels = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> updateLabels()));
        checkWave = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> checkClearance()));
        updateLabels.setCycleCount(-1);
        updateLabels.play();
        checkWave.setCycleCount(-1);
        checkWave.play();

        createStage(stage);
        aircraft.requestFocus();
    }

    private void setLabels() {
        scores.setLayoutX(10);
        scores.setLayoutY(10);
        scores.setTextFill(Color.WHITE);
        scores.setText("Score: " + game.getScore());
        pane.getChildren().add(scores);

        kills.setLayoutX(10);
        kills.setLayoutY(20);
        kills.setTextFill(Color.WHITE);
        kills.setText("Kills: " + game.getKills());
        pane.getChildren().add(kills);

        nukes.setLayoutX(10);
        nukes.setLayoutY(30);
        nukes.setTextFill(Color.WHITE);
        pane.getChildren().add(nukes);

        clusters.setLayoutX(10);
        clusters.setLayoutY(40);
        clusters.setTextFill(Color.WHITE);
        pane.getChildren().add(clusters);

        waveLabel.setLayoutX(game.WIDTH / 2);
        waveLabel.setLayoutY(10);
        waveLabel.setTextFill(Color.WHITE);
        waveLabel.setText("Wave " + game.getWave());
        pane.getChildren().add(waveLabel);
    }

    private void updateLabels() {
        kills.setText("Kills: " + game.getKills());
        scores.setText("Score: " + game.getScore());
        nukes.setText("Nukes: " + game.getNukeNumber());
        clusters.setText("Cluster: " + game.getClusterNumber());
    }

    private void createTrees() {
        for (int i = 0; i < 3; i++) {
            Tree tree = new Tree();
            game.trees.getChildren().add(tree);
        }
    }

    private void createTrench() {
        trench = new Trench();
        pane.getChildren().add(trench);
        game.setTrench(trench);
    }

    private void createBuilding() {
        building = new Building();
        pane.getChildren().add(building);
        game.setBuilding(building);
    }

    private void createObjects() {
        createTrucks = new Timeline(new KeyFrame(Duration.seconds(5), actionEvent -> createTruck()));
        createTrucks.setCycleCount(2);
        createTrucks.play();

        createTanks = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> createTank(false, 0)));
        createTanks.setCycleCount(3);
        createTanks.play();

        createTanks.setOnFinished(actionEvent -> game.setMadeEveryThing(true));
    }

    private void createMig(int radios) {
        Mig mig = new Mig(radios);
        mig.setMigAnimation(new MigAnimation(pane, game, mig));
        game.addToAnimation(mig.getMigAnimation());
        mig.getMigAnimation().play();
        pane.getChildren().add(mig);
    }

    private void createTruck() {
        Truck truck = new Truck();
        truck.setTruckAnimation(new TruckAnimation(game, truck));
        game.addToAnimation(truck.getTruckAnimation());
        truck.getTruckAnimation().play();
        game.getTrucks().getChildren().add(truck);
    }

    private void createTank(boolean shooter, int radios) {
        Tank tank;
        if (!shooter) {
            tank = new Tank();
        } else tank = new Tank(radios);
        tank.setTankAnimation(new TankAnimation(pane, game, tank, shooter));
        game.addToAnimation(tank.getTankAnimation());
        tank.getTankAnimation().play();
        game.tanks.getChildren().add(tank);
    }

    private void setGameSetting() {
        gameSetting = game.getUser().getGameSetting();
        setKeys();
    }

    private void setKeys() {
        up = gameSetting.getUp();
        down = gameSetting.getDown();
        left = gameSetting.getLeft();
        right = gameSetting.getRight();
        bombing = gameSetting.getBomb();
    }

    private void createAircraft() {
        aircraft = new Aircraft(game);
        game.setAircraft(aircraft);
        AircraftMovementAnimation aircraftMovementAnimation = new AircraftMovementAnimation(pane, game, aircraft);
        aircraft.setAircraftTransition(aircraftMovementAnimation);
        aircraftMovementAnimation.play();

        aircraft.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == bombing) {
                shoot();
                game.setBombShot(game.getBombShot() + 1);
            } else if (keyEvent.getCode() == KeyCode.R) {
                if (game.getNukeNumber() > 0) {
                    shootNuke();
                    game.setNukeNumber(game.getNukeNumber() - 1);
                    game.setBombShot(game.getBombShot() + 1);
                }
            } else if (keyEvent.getCode() == KeyCode.G) {
                game.setNukeNumber(game.getNukeNumber() + 1);
            } else if (keyEvent.getCode() == KeyCode.C) {
                if (game.getClusterNumber() > 0) {
                    shootCluster();
                    game.setClusterNumber(game.getClusterNumber() - 1);
                    game.setBombShot(game.getBombShot() + 1);
                }
            } else if (keyEvent.getCode() == KeyCode.CONTROL) {
                game.setClusterNumber(game.getClusterNumber() + 1);
            } else if (keyEvent.getCode() == KeyCode.H) {
                if (game.getHp() < 3) {
                    game.setHp(game.getHp() + 1);
                }
            } else if (keyEvent.getCode() == KeyCode.T) {
                if (game.getWave() == 1) {
                    createTank(false, 0);
                } else {
                    createTank(true, 200);
                }
            } else if (keyEvent.getCode() == KeyCode.P) {
                destroyEverything();
            }
        });

        aircraft.setOnKeyPressed(keyEvent -> {
            aircraftAngle = (int) aircraft.getRotate();
            int angle = aircraftAngle % 360;
            if (angle < 0) angle += 360;
            if (keyEvent.getCode() == right) {
                if (angle != 0) {
                    if (angle >= 180) {
                        aircraftAngle += 10;
                    } else aircraftAngle -= 10;
                }
            } else if (keyEvent.getCode() == left) {
                if (angle != 180) {
                    if (angle > 180 || angle == 0) {
                        aircraftAngle -= 10;
                    } else aircraftAngle += 10;
                }
            } else if (keyEvent.getCode() == up) {
                if (angle != 270) {
                    if (angle > 270 || angle <= 90) {
                        aircraftAngle -= 10;
                    } else aircraftAngle += 10;
                }
            } else if (keyEvent.getCode() == down) {
                if (angle != 90) {
                    if (angle > 90 && angle <= 270) {
                        aircraftAngle -= 10;
                    } else aircraftAngle += 10;
                }
            }
            aircraft.setRotate(aircraftAngle);
        });
    }

    private void destroyEverything() {
        createTrucks.stop();
        createTanks.stop();
        game.setMadeEveryThing(true);

        while (!game.tanks.getChildren().isEmpty()) {
            pane.getChildren().remove(game.tanks.getChildren().getLast());
            game.tanks.getChildren().removeLast();
        }

        while (!game.getTrucks().getChildren().isEmpty()) {
            pane.getChildren().remove(game.getTrucks().getChildren().getLast());
            game.getTrucks().getChildren().removeLast();
        }

        pane.getChildren().remove(game.getBuilding());
        pane.getChildren().remove(game.getTrench());

        game.getBuilding().setHit(true);
        game.getTrench().setHit(true);
    }

    public void checkClearance() {
        if (game.tanks.getChildren().isEmpty() && game.getTrucks().getChildren().isEmpty()
                && game.getBuilding().isHit() && game.getTrench().isHit() && game.isMadeEveryThing()) {
            game.setMadeEveryThing(false);
            checkWave.pause();
            Label accuracy = new Label();
            while (!game.trees.getChildren().isEmpty()) {
                game.trees.getChildren().removeLast();
            }
            if (game.getWave() == 1) {
                game.setFirstWaveKills(game.getKills());
                game.setFirstWaveAccuracy((double) game.getKills() / game.getBombShot());
                game.setWave(2);
                game.setTotalBombShot(game.getBombShot());
                waveLabel.setText("Wave 2");

                accuracy.setText("Accuracy: " + (int) (game.getFirstWaveAccuracy() * 100) + "%");
            } else if (game.getWave() == 2) {
                game.setSecondWaveKills(game.getKills() - game.getFirstWaveKills());
                game.setSecondWaveAccuracy((double) game.getSecondWaveKills() / game.getBombShot());
                game.setWave(3);
                game.setTotalBombShot(game.getTotalBombShot() + game.getBombShot());
                waveLabel.setText("Wave 3");

                accuracy.setText("Accuracy: " + (int) (game.getSecondWaveAccuracy() * 100) + "%");
            } else if (game.getWave() == 3) {
                game.setThirdWaveKills(game.getKills() - game.getSecondWaveKills() - game.getFirstWaveKills());
                game.setThirdWaveAccuracy((double) game.getThirdWaveKills() / game.getBombShot());
                game.setWave(4);
                game.setTotalBombShot(game.getTotalBombShot() + game.getBombShot());
                waveLabel.setText("Finished");

                accuracy.setText("Accuracy: " + (int) (game.getThirdWaveAccuracy() * 100) + "%");
            }

            game.setBombShot(0);

            accuracy.setCenterShape(true);
            accuracy.setTextFill(Color.CYAN);
            accuracy.setLayoutX(game.WIDTH / 2 - 50);
            accuracy.setLayoutY(100);
            accuracy.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
            pane.getChildren().add(accuracy);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
                pane.getChildren().remove(accuracy);
                if (game.getWave() < 4) {
                    createTrees();
                    createBuilding();
                    createTrench();
                    createObjectsWave2();
                    checkWave.play();
                } else {
                    try {
                        winGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }));
            timeline.play();
        }
    }

    private void winGame() throws Exception {
        game.setWon(true);
        mediaPlayer.pause();
        aircraft.getAircraftTransition().stop();
        WinMenu winMenu = new WinMenu();
        winMenu.start(gameStage);
    }

    public void looseGame() throws Exception {
        game.setWon(false);
        mediaPlayer.pause();
        aircraft.getAircraftTransition().stop();
        WinMenu winMenu = new WinMenu();
        winMenu.start(gameStage);
    }

    private void createObjectsWave2() {
        createTrucks = new Timeline(new KeyFrame(Duration.seconds(5), actionEvent -> createTruck()));
        createTrucks.setCycleCount(2);
        createTrucks.play();

        createTanks = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> createTank(true, 200)));
        createTanks.setCycleCount(3);
        createTanks.play();

        createTanks.setOnFinished(actionEvent -> game.setMadeEveryThing(true));

        if (game.getWave() == 3) {
            createMigs = new Timeline(new KeyFrame(Duration.seconds(40), actionEvent -> createMig(100)));
            createMigs.setCycleCount(-1);
            createMigs.play();
        }
    }

    private void shootCluster() {
        for (int i = 1; i < 4; i++) {
            bomb = new Bomb();
            bomb.setBombAnimation(new BombAnimation(pane, game, bomb, i));
            pane.getChildren().add(bomb);
            bomb.getBombAnimation().play();
        }
    }

    private void shoot() {
        bomb = new Bomb(aircraft);
        int angle = aircraftAngle % 360;
        if (angle < 0) angle += 360;
        bomb.setBombAnimation(new BombAnimation(pane, game, bomb, angle > 90 && angle < 270));
        pane.getChildren().add(bomb);
        bomb.getBombAnimation().play();
    }

    private void shootNuke() {
        nuclear = new Nuclear(aircraft);
        nuclear.setNukeAnimation(new NukeAnimation(pane, game, nuclear));
        pane.getChildren().add(nuclear);
        nuclear.getNukeAnimation().play();
    }

    private void createPane() {
        pane = new Pane();
        setSize(pane);
        pane.setBackground(new Background(createBackGroundImage()));
    }

    private void createStage(Stage stage) {
        Scene scene = new Scene(pane);
        gameStage.getIcons().add(new Image(Objects.requireNonNull(GameLauncher.class.getResourceAsStream("/Image/Icon/main.png"))));
        gameStage.setScene(scene);
        gameStage.centerOnScreen();
        gameStage.show();
    }

    private void setSize(Pane pane) {
        pane.setMinHeight(game.HEIGHT);
        pane.setMinWidth(game.WIDTH);
        pane.setMaxHeight(game.HEIGHT);
        pane.setMaxWidth(game.WIDTH);
    }

    private BackgroundImage createBackGroundImage() {
        Image image = new Image(Objects.requireNonNull(Objects.requireNonNull(GameLauncher.class.getResource("/Image/Objects/bg.png")).toExternalForm()), game.WIDTH, game.HEIGHT, false, false);
        return new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
    }

    public void makeMigAlert() {
        migAlert.setText("Mig is coming!");
        migAlert.setCenterShape(true);
        migAlert.setTextFill(Color.CYAN);
        migAlert.setLayoutX(game.WIDTH / 2 - 50);
        migAlert.setLayoutY(100);
        migAlert.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
        pane.getChildren().add(migAlert);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
            pane.getChildren().remove(migAlert);
        }));
    }
}
