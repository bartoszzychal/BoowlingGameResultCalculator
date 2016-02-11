package com.capgemini.boowlingGameResultCalculator;

import java.util.LinkedList;

public class BoowlingGameResultCalculator implements BoowlingGameResultCalculatorInterface {

	private final int MAX_NUMBER_OF_NORMAL_FRAMES = 9;
	private final int MAX_NUMBER_OF_LAST_FRAMES = 1;
	private final int MAX_NUMBER_OF_FRAMES = MAX_NUMBER_OF_NORMAL_FRAMES + MAX_NUMBER_OF_LAST_FRAMES;

	private LinkedList<Frame> listOfFrames;
	private FrameFactory frameFactory;
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
	
	private Frame getNewFrame(){
		return frameFactory.getFrame(++frameCounter);
	}

	@Override
	public void roll(int numberOfPins) {
		if (!isFinished()) {
			Frame lastCreatedFrame = getCurrentFrame();
			if (lastCreatedFrame.isFinished()) {
				addNewFrame();
				lastCreatedFrame = getCurrentFrame();
			}
			lastCreatedFrame.addRoll(numberOfPins);
		}
	}

	private void addNewFrame() {
		Frame newFrame = getNewFrame();
		updateNextFrameForCurrentFrame(newFrame);
		listOfFrames.add(newFrame);
	}

	private void updateNextFrameForCurrentFrame(Frame nextFrame) {
		getCurrentFrame().setNextFrame(nextFrame);
	}

	private Frame getCurrentFrame() {
		return listOfFrames.getLast();
	}

	@Override
	public int score() {
		Integer score = new Integer(0);
		for (Frame frame : listOfFrames) {
			score += frame.score();
		}
		return score;
	}

	@Override
	public boolean isFinished() {
		if (listOfFrames.size() == MAX_NUMBER_OF_FRAMES && listOfFrames.get(MAX_NUMBER_OF_FRAMES - 1).isFinished()) {
			return true;
		}
		return false;
	}

}
