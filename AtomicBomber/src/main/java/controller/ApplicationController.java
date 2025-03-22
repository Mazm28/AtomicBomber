package controller;

import java.util.Random;

public class ApplicationController {
    private static final Random random = new Random();

    public static Random getRandom() {
        return random;
    }
}
