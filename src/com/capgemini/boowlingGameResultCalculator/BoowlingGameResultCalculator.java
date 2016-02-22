package com.capgemini.boowlingGameResultCalculator;

import java.util.LinkedList;

public class BoowlingGameResultCalculator implements BoowlingGameResultCalculatorInterface {

    private final int MAX_NUMBER_OF_NORMAL_FRAMES = 9;
    private final int MAX_NUMBER_OF_LAST_FRAMES = 1;
    private final int MAX_NUMBER_OF_FRAMES = MAX_NUMBER_OF_NORMAL_FRAMES + MAX_NUMBER_OF_LAST_FRAMES;

    // REVIEW bzychal - the variables which don't change should be declared final
    private final LinkedList<Frame> listOfFrames;
    private final FrameFactory frameFactory;
    private Integer frameCounter;

    public BoowlingGameResultCalculator() {
        listOfFrames = new LinkedList<>();
        frameFactory = new FrameFactory();
        frameCounter = Integer.valueOf(0);
        createFirstFrame();
    }

    private void createFirstFrame() {
        Frame newFrame = getNewFrame();
        listOfFrames.add(newFrame);
    }

    // REVIEW bzychal -
    private Frame getNewFrame() {
        ++frameCounter;
        return frameFactory.getFrame(frameCounter);
    }

    @Override
    public void roll(int numberOfPins) {
        if (!isFinished()) {
            // REVIEW bzychal - this code is badly written, we have two calls to getCurrentFrame which return different
            // results each time, better would be for the addNewFrame method to return the new frame which was created
            // as side effect.
            Frame currentFrame = getCurrentFrame();
            if (currentFrame.isFinished()) {
                addNewFrame();
                currentFrame = getCurrentFrame();
            }
            currentFrame.addRoll(numberOfPins);
        }
        // REVIEW bzychal - what in case when the game is finished and smn rolls a ball? Should not an exception be
        // thrown here?
    }

    private void addNewFrame() {
        Frame newFrame = getNewFrame();
        updateNextFrameForCurrentFrame(newFrame);
        listOfFrames.add(newFrame);
    }

    private void updateNextFrameForCurrentFrame(Frame nextFrame) {
        // REVIEW bzychal - the exception should not be caught here, it is ok for it to terminate the program
        try {
            getCurrentFrame().setNextFrame(nextFrame);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Frame getCurrentFrame() {
        return listOfFrames.getLast();
    }

    @Override
    public int score() {
        Integer score = new Integer(0);
        // REVIEW bzychal - please use Streams and the reduce method from Java 8 for this calculation
        for (Frame frame : listOfFrames) {
            score += frame.score();
        }
        return score;
    }

    @Override
    public boolean isFinished() {
        return listOfFrames.size() == MAX_NUMBER_OF_FRAMES && listOfFrames.get(MAX_NUMBER_OF_FRAMES - 1).isFinished();

    }

}
