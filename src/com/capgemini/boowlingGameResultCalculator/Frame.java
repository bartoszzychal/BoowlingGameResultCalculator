package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public abstract class Frame {

	public enum BONUS {
		NO_BONUS, STRIKE, SPARE;
	}

	protected final int MIN_NUMBER_PINS_IN_ONE_ROLL = 0;
	protected final int MAX_NUMBER_PINS_IN_ONE_ROLL = 10;
	
	protected ArrayList<Integer> rolls;
	protected BONUS bonus;
	protected Frame nextFrame;

	public abstract void addRoll(int numberOfPins);
	public abstract Integer score();
	public abstract Boolean isFinished();
	public abstract void setNextFrame(Frame frame);

	protected Integer sumOfRolls() {
		Integer sum = Integer.valueOf(0);
		for (Integer pins : rolls) {
			sum += pins;
		}
		return sum;
	}
	
	protected void setBonusIfShouldBe(Integer numberOfPins) {
		if (numberOfPins == MAX_NUMBER_PINS_IN_ONE_ROLL) {
			bonus = BONUS.STRIKE;
		} else if (sumOfRolls() == MAX_NUMBER_PINS_IN_ONE_ROLL) {
			bonus = BONUS.SPARE;
		}
	}
	
	protected Integer getFirstRollIfExistsOtherReturnZero() {
		if (rolls.size() > 0) {
			return rolls.get(0);
		}
		return 0;
	}

	protected Integer getSecondRollIfExistsOtherReturnZero() {
		if (rolls.size() > 1) {
			return rolls.get(1);
		}
		return 0;
	};
	
	protected Boolean nextFrameExist() {
		if (nextFrame != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	protected Frame getNextFrame(){
		return nextFrame;
	};
	
	protected BONUS getBonus() {
		return BONUS.NO_BONUS;
	}

}
