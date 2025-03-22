package model;

import javafx.scene.input.KeyCode;

public class GameSetting {
    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    private KeyCode bomb;
    private boolean blackAndWhite;
    private double tankSpeed;
    private int tankRadius;
    private int migRadius;
    private int migTimeSpan;

    public GameSetting() {
        up = KeyCode.UP;
        down = KeyCode.DOWN;
        left = KeyCode.LEFT;
        right = KeyCode.RIGHT;
        bomb = KeyCode.SPACE;
        blackAndWhite = false;
        tankSpeed = 0.5;
        tankRadius = 10;
        migRadius = 10;
        migTimeSpan = 15;
    }

    public KeyCode getDown() {
        return down;
    }

    public KeyCode getLeft() {
        return left;
    }

    public KeyCode getRight() {
        return right;
    }

    public KeyCode getUp() {
        return up;
    }

    public KeyCode getBomb() {
        return bomb;
    }

    public void changeKeys(int form) {
        if (form == 1) {
            up = KeyCode.UP;
            down = KeyCode.DOWN;
            left = KeyCode.LEFT;
            right = KeyCode.RIGHT;
        } else if (form == 2) {
            up = KeyCode.W;
            down = KeyCode.S;
            left = KeyCode.A;
            right = KeyCode.D;
        }
    }

    public void changeColors() {
        blackAndWhite = !blackAndWhite;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }
}
