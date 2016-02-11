package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class LastFrame extends Frame{

	private final int MIN_NUMBER_OF_ROLLS = 2;
	private final int MAX_NUMBER_OF_ROLLS = 3;
	private final int MAX_NUMBER_PINS_IN_LAST_FRAME = 30;
	
	public LastFrame() {
		rolls = new ArrayList<>(MAX_NUMBER_OF_ROLLS);
		bonus = BONUS.NO_BONUS;
		nextFrame = null;
	}
	
	@Override
	public Integer score() {
		Integer score = sumOfRolls();
		return score;
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
		
		if ((sumOfRolls() + numberOfPins > MAX_NUMBER_PINS_IN_ONE_ROLL) && BONUS.NO_BONUS.equals(bonus)) {
			throw new IllegalArgumentException(
					"Number of pins in two rolls can not be bigger then " +MAX_NUMBER_PINS_IN_ONE_ROLL);
		}
	
		if (sumOfRolls() + numberOfPins > MAX_NUMBER_PINS_IN_LAST_FRAME) {
			throw new IllegalArgumentException(
					"Number of pins in last frame can not be bigger then " + MAX_NUMBER_PINS_IN_LAST_FRAME);
		}

		rolls.add(numberOfPins);
		setBonusIfShouldBe(numberOfPins);
	}

	@Override
	public Boolean isFinished() {
		return (bonus.equals(BONUS.NO_BONUS)&&rolls.size() == MIN_NUMBER_OF_ROLLS ) || (rolls.size()==MAX_NUMBER_OF_ROLLS);
	}

	@Override
	public void setNextFrame(Frame frame) {
	}

}
