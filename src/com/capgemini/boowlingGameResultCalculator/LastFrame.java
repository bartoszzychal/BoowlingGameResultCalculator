package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class LastFrame extends Frame{

	private final int MIN_NUMBER_OF_ROLLS = 2;
	private final int MAX_NUMBER_OF_ROLLS = 3;
	
	public LastFrame() {
		rolls = new ArrayList<>(MAX_NUMBER_OF_ROLLS);
	}
	
	@Override
	public Integer score() {
		return sumOfRolls();
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
		
		if ((sumOfRolls() + numberOfPins > MAX_NUMBER_PINS_IN_ONE_ROLL) && !isSpare() && !isStrike()) {
			throw new IllegalArgumentException(
					"Number of pins in two rolls can not be bigger then " +MAX_NUMBER_PINS_IN_ONE_ROLL);
		}
	
		rolls.add(numberOfPins);
	}
	
	@Override
	public Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame) {
		return getFirstRoll() + getSecondRollIfExistsOtherReturnZero();
	};
	
	@Override
	public Boolean isFinished() {
		return (!isSpare()&&!isStrike()&&rolls.size() == MIN_NUMBER_OF_ROLLS ) || (rolls.size()==MAX_NUMBER_OF_ROLLS);
	}
	
	protected boolean isStrike() {
		return rolls.get(0) == MAX_NUMBER_PINS_IN_ONE_ROLL;
	}

	@Override
	public void setNextFrame(Frame frame) throws IllegalAccessException {
		throw new IllegalAccessException("Last frame can not have next frame");
	}

}
