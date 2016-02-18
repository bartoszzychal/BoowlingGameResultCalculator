package com.capgemini.boowlingGameResultCalculator;

public class FrameFactory {

    private final int MAX_NUMBER_FRAMES = 10;

    // REVIEW bzychal - inaccurate naming, it should be createFrame
    Frame getFrame(Integer frameCounter) {
        if (frameCounter == MAX_NUMBER_FRAMES) {
            return new LastFrame();
        }
        return new NormalFrame();
    }

}
