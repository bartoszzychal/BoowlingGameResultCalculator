package com.capgemini.boowlingGameResultCalculator;


public interface Frame {
	
	public enum BONUS {
		NO_BONUS, STRIKE, SPARE;
	}
	
	final int MIN_NUMBER_PINS_IN_ONE_ROLL = 0;
	final int MAX_NUMBER_PINS_IN_ONE_ROLL = 10;
	
	public void addRoll(int numberOfPins);
	public void setNextFrame(Frame frame);
	public Frame getNextFrame();
	public Boolean nextFrameExist();
	public Integer score();
	public Boolean isFinished();
	public BONUS getBonus();
	public Integer getFirstRollIfExistsOtherReturnZero();
	public Integer getSecondRollIfExistsOtherReturnZero();
}
