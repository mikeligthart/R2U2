package edu.radboud.ai.r2u2.action.util;

import java.util.Random;

/**
 * @author Mike Ligthart
 */

public enum LedColor {
    BLUE, CYAN, GREEN, ORANGE, RED, WHITE, YELLOW, OFF;

    public static LedColor random() {
        LedColor[] val = LedColor.values();
        Random r = new Random();
        return val[r.nextInt(val.length)];
    }
}
