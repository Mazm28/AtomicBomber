package model;

import javafx.animation.Transition;
import javafx.scene.Group;
import model.GameObjects.Aircraft;
import model.GameObjects.Building;
import model.GameObjects.Trench;
import view.GameLauncher;

import java.util.ArrayList;

public class Game {
    public final double WIDTH = 800;
    public final double HEIGHT = 600;
    private User user;
    private int score = 0;
    private GameLauncher gameLauncher;
    private final ArrayList<Transition> animations = new ArrayList<>();
    private Aircraft aircraft;
    private final Group trucks = new Group();
    public final Group tanks = new Group();
    public final Group trees = new Group();
    private Building building;
    private Trench trench;
    private int nukeNumber;
    private int clusterNumber;
    private int hp;
    private int kills = 0;
    private int wave = 1;
    private int bombShot;
    private int totalBombShot;
    private int firstWaveKills = 0;
    private double firstWaveAccuracy;
    private int secondWaveKills = 0;
    private double secondWaveAccuracy;
    private int thirdWaveKills = 0;
    private double thirdWaveAccuracy;
    private boolean madeEveryThing = false;
    private static Game lastGame;
    private boolean won;

    public Game(User user, GameLauncher gameLauncher) {
        this.user = user;
        this.gameLauncher = gameLauncher;
        nukeNumber = 0;
        hp = 3;
    }

    public User getUser() {
        return user;
    }

    public void addToAnimation(Transition animation) {
        animations.add(animation);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setTrench(Trench trench) {
        this.trench = trench;
    }

    public void setNukeNumber(int nukeNumber) {
        this.nukeNumber = nukeNumber;
    }

    public int getNukeNumber() {
        return nukeNumber;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getKills() {
        return kills;
    }

    public int getWave() {
        return wave;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Building getBuilding() {
        return building;
    }

    public Trench getTrench() {
        return trench;
    }

    public int getScore() {
        return score;
    }

    public void setBombShot(int bombShot) {
        this.bombShot = bombShot;
    }

    public int getBombShot() {
        return bombShot;
    }

    public void setFirstWaveKills(int firstWaveKills) {
        this.firstWaveKills = firstWaveKills;
    }

    public int getFirstWaveKills() {
        return firstWaveKills;
    }

    public void setFirstWaveAccuracy(double firstWaveAccuracy) {
        this.firstWaveAccuracy = firstWaveAccuracy;
    }

    public double getFirstWaveAccuracy() {
        return firstWaveAccuracy;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public Group getTrucks() {
        return trucks;
    }

    public boolean isMadeEveryThing() {
        return madeEveryThing;
    }

    public void setMadeEveryThing(boolean madeEveryThing) {
        this.madeEveryThing = madeEveryThing;
    }

    public void setSecondWaveAccuracy(double secondWaveAccuracy) {
        this.secondWaveAccuracy = secondWaveAccuracy;
    }

    public void setSecondWaveKills(int secondWaveKills) {
        this.secondWaveKills = secondWaveKills;
    }

    public double getSecondWaveAccuracy() {
        return secondWaveAccuracy;
    }

    public int getSecondWaveKills() {
        return secondWaveKills;
    }

    public void setThirdWaveAccuracy(double thirdWaveAccuracy) {
        this.thirdWaveAccuracy = thirdWaveAccuracy;
    }

    public void setThirdWaveKills(int thirdWaveKills) {
        this.thirdWaveKills = thirdWaveKills;
    }

    public double getThirdWaveAccuracy() {
        return thirdWaveAccuracy;
    }

    public int getThirdWaveKills() {
        return thirdWaveKills;
    }

    public void looseGame() throws Exception {
        gameLauncher.looseGame();
    }

    public void setTotalBombShot(int totalBombShot) {
        this.totalBombShot = totalBombShot;
    }

    public int getTotalBombShot() {
        return totalBombShot;
    }

    public static void setLastGame(Game lastGame) {
        Game.lastGame = lastGame;
    }

    public static Game getLastGame() {
        return lastGame;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public GameLauncher getGameLauncher() {
        return gameLauncher;
    }
}
