package com.capgemini.boowlingGameResultCalculator;

public class FrameFactory {

    private final int MAX_NUMBER_FRAMES = 10;

    Frame createFrame(Integer frameCounter) {
        if (frameCounter == MAX_NUMBER_FRAMES) {
            return new LastFrame();
        }
        return new NormalFrame();
    }

}
