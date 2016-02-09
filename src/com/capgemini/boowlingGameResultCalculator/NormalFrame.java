package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class NormalFrame extends Frame {

	private final int MAX_NUMBER_OF_ROLLS = 2;
	private final int MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME = 10;

	public NormalFrame() {
		rolls = new ArrayList<>(MAX_NUMBER_OF_ROLLS);
		bonus = BONUS.NO_BONUS;
		nextFrame = null;
	}

	@Override
	public Integer score() {
		Integer score = sumOfRolls();
		if (!BONUS.NO_BONUS.equals(bonus)) {
			score += getBonusValue();
		}
		return score;
	}

	private Integer getBonusValue() {
		Integer bonusValue = Integer.valueOf(0);
		if (nextFrameExist()) {
			bonusValue = countBonus();
		}
		return bonusValue;
	}

	private Integer countBonus() {
		Integer bonusValue = Integer.valueOf(0);
		if (BONUS.SPARE.equals(bonus)) {
			bonusValue = getBonusForSpare();
		} else if (BONUS.STRIKE.equals(bonus)) {
			bonusValue = getBonusForStrike();
		}
		return bonusValue;
	}

	private Integer getBonusForSpare() {
		Integer bonusValue;
		bonusValue = nextFrame.getFirstRollIfExistsOtherReturnZero();
		return bonusValue;
	}

	private Integer getBonusForStrike() {
		
		Integer bonusValue = Integer.valueOf(0);
		BONUS nextFrameBonus = nextFrame.getBonus();
		
		if (nextFrameBonus.equals(BONUS.SPARE) || nextFrameBonus.equals(BONUS.NO_BONUS)) {
			bonusValue = nextFrame.getFirstRollIfExistsOtherReturnZero()
					   + nextFrame.getSecondRollIfExistsOtherReturnZero();
		}
		if (nextFrameBonus.equals(BONUS.STRIKE)) {
			bonusValue = nextFrame.getFirstRollIfExistsOtherReturnZero();
			if (nextFrame.nextFrameExist()) {
				bonusValue += nextFrame.getNextFrame().getFirstRollIfExistsOtherReturnZero();
			}
			
		}
		return bonusValue;
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

		if (sumOfRolls() + numberOfPins > MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME) {
			throw new IllegalArgumentException(
					"Number of pins in one frame can not be bigger then " + MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME);
		}

		rolls.add(numberOfPins);
		setBonusIfShouldBe(numberOfPins);
	}

	@Override
	public Boolean isFinished() {
		return sumOfRolls().equals(MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME) || (rolls.size() == MAX_NUMBER_OF_ROLLS);
	}

	public BONUS getBonus() {
		return bonus;
	}

	public void setNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}

}
