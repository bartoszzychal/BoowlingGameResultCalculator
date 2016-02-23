package com.capgemini.boowlingGameResultCalculator;

import java.util.LinkedList;

public class BoowlingGameResultCalculator implements BoowlingGameResultCalculatorInterface {

    private final int MAX_NUMBER_OF_NORMAL_FRAMES = 9;
    private final int MAX_NUMBER_OF_LAST_FRAMES = 1;
    private final int MAX_NUMBER_OF_FRAMES = MAX_NUMBER_OF_NORMAL_FRAMES + MAX_NUMBER_OF_LAST_FRAMES;

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

    private Frame getNewFrame() {
        frameCounter += 1;
        return frameFactory.createFrame(frameCounter);
    }

    @Override
    public void roll(int numberOfPins) throws IllegalAccessException {
        if (!isFinished()) {
            Frame currentFrame = getCurrentFrame();
            if (currentFrame.isFinished()) {
                currentFrame = addNewFrame();
            }
            currentFrame.addRoll(numberOfPins);
        }else{
            throw new IllegalAccessException("The game is finished");
        }
    }

    private Frame addNewFrame() {
        Frame newFrame = getNewFrame();
        updateNextFrameForCurrentFrame(newFrame);
        listOfFrames.add(newFrame);
        return newFrame;
    }

    private void updateNextFrameForCurrentFrame(Frame nextFrame) {
            getCurrentFrame().setNextFrame(nextFrame);
    }

    private Frame getCurrentFrame() {
        return listOfFrames.getLast();
    }

    @Override
    public int score() {
       return listOfFrames.stream().map(Frame::score).reduce((score1,score2)->score1+score2).get();
    }

    @Override
    public boolean isFinished() {
        return listOfFrames.size() == MAX_NUMBER_OF_FRAMES && listOfFrames.get(MAX_NUMBER_OF_FRAMES - 1).isFinished();

    }

}
