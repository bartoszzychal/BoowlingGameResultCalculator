package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class LastFrame implements Frame {

	private final int MIN_NUMBER_OF_ROLLS = 2;
	private final int MAX_NUMBER_OF_ROLLS = 3;
	private final int MAX_NUMBER_PINS_IN_ONE_FRAME = 30;

	private ArrayList<Integer> rolls;
	private BONUS bonus;
	
	public LastFrame() {
		rolls = new ArrayList<>();
		bonus = BONUS.NO_BONUS;
	}
	
	@Override
	public Integer score() {
		Integer score = sumOfRolls();
		return score;
	}
	
	private Integer sumOfRolls() {
		Integer sum = Integer.valueOf(0);
		for (Integer pins : rolls) {
			sum += pins;
		}
		return sum;
	}

	@Override
	public void addRoll(int numberOfPins) throws IllegalArgumentException {
		if (numberOfPins < MIN_NUMBER_PINS_IN_ONE_ROLL) {
			throw new IllegalArgumentException("Number of pins can not be negative number");
		}

		if (numberOfPins > MAX_NUMBER_PINS_IN_ONE_ROLL) {
			throw new IllegalArgumentException(
					"Number of pins can be bigger then " + MAX_NUMBER_PINS_IN_ONE_ROLL + " in one rolls");
		}

		if (sumOfRolls() + numberOfPins > MAX_NUMBER_PINS_IN_ONE_FRAME) {
			throw new IllegalArgumentException(
					"Number of pins in one frame can not be bigger then " + MAX_NUMBER_PINS_IN_ONE_FRAME);
		}

		rolls.add(numberOfPins);
		setBonusIfShouldBe(numberOfPins);
	}

	private void setBonusIfShouldBe(Integer numberOfPins) {
		if (numberOfPins == MAX_NUMBER_PINS_IN_ONE_ROLL) {
			bonus = BONUS.STRIKE;
		} else if (sumOfRolls() == MAX_NUMBER_PINS_IN_ONE_ROLL) {
			bonus = BONUS.SPARE;
		}	
	}
	
	@Override
	public Boolean isFinished() {
		return (bonus.equals(BONUS.NO_BONUS)&&rolls.size() == MIN_NUMBER_OF_ROLLS ) || (rolls.size()==MAX_NUMBER_OF_ROLLS);
	}

	@Override
	public Boolean nextFrameExist() {
		return Boolean.FALSE;
	}
	
	@Override
	public BONUS getBonus() {
		return BONUS.NO_BONUS;
	}

	@Override
	public Integer getFirstRollIfExistsOtherReturnZero() {
		if (rolls.size() > 0) {
			return rolls.get(0);
		}
		return 0;
	}

	@Override
	public Integer getSecondRollIfExistsOtherReturnZero() {
		if (rolls.size() > 1) {
			return rolls.get(1);
		}
		return 0;
	}

	@Override
	public Frame getNextFrame() {
		return null;
	}
	
	@Override
	public void setNextFrame(Frame frame) {
	}


}
