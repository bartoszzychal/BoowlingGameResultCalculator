package com.capgemini.boowlingGameResultCalculator;

public interface Frame {

	void addRoll(int numberOfPins);

	void setNextFrame(Frame frame) throws IllegalAccessException;

	Integer score();

	Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame);

	Integer getFirstRoll();
	
	Boolean isFinished();

	
}