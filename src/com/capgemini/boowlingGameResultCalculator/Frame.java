package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public abstract class Frame {

	protected final int MIN_NUMBER_PINS_IN_ONE_ROLL = 0;
	protected final int MAX_NUMBER_PINS_IN_ONE_ROLL = 10;
	
	protected ArrayList<Integer> rolls;
	protected Frame nextFrame;
	

	public abstract void addRoll(int numberOfPins);
	public abstract Integer score();
	public abstract Boolean isFinished();
	public abstract void setNextFrame(Frame frame) throws IllegalAccessException;
	public abstract Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame);
	protected abstract boolean isStrike();

	protected boolean isSpare() {
		return sumOfRolls().equals(MAX_NUMBER_PINS_IN_ONE_ROLL)&&rolls.size() == 2;
	}

	
	protected Integer sumOfRolls() {
		Integer sum = Integer.valueOf(0);
		for (Integer pins : rolls) {
			sum += pins;
		}
		return sum;
	}
	
	protected Integer getFirstRoll() {
		return rolls.get(0);
	}

	protected Integer getSecondRollIfExistsOtherReturnZero() {
		if (rolls.size() > 1) {
			return rolls.get(1);
		}
		return 0;
	};	
}
