package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class NormalFrame implements Frame {

	private final int MAX_NUMBER_OF_ROLLS = 2;
	private final int MAX_NUMBER_PINS_IN_ONE_FRAME = 10;
	
	private Frame nextFrame;
	private ArrayList<Integer> rolls;
	private BONUS bonus;

	public NormalFrame() {
		rolls = new ArrayList<>();
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

	private Integer sumOfRolls() {
		Integer sum = Integer.valueOf(0);
		for (Integer pins : rolls) {
			sum += pins;
		}
		return sum;
	}

	private Integer getBonusValue() {
		Integer bonusValue = Integer.valueOf(0);
		if (nextFrameExist()) {
			bonusValue = countBonus();
		}
		return bonusValue;
	}

	public Boolean nextFrameExist() {
		if (nextFrame != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
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
		
		if (!nextFrameBonus.equals(BONUS.STRIKE)) {
			bonusValue = nextFrame.getFirstRollIfExistsOtherReturnZero()
					   + nextFrame.getSecondRollIfExistsOtherReturnZero();
		} else if (nextFrameBonus.equals(BONUS.STRIKE)) {
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

		if (sumOfRolls() + numberOfPins > MAX_NUMBER_PINS_IN_ONE_FRAME) {
			throw new IllegalArgumentException(
					"Number of pins in one frame can not be bigger then " + MAX_NUMBER_PINS_IN_ONE_FRAME);
		}

		rolls.add(numberOfPins);
		setBonusIfShouldBe(numberOfPins);
	}

	private void setBonusIfShouldBe(Integer numberOfPins) {
		if (numberOfPins == MAX_NUMBER_PINS_IN_ONE_FRAME) {
			bonus = BONUS.STRIKE;
		} else if (sumOfRolls() == MAX_NUMBER_PINS_IN_ONE_FRAME) {
			bonus = BONUS.SPARE;
		}
	}

	@Override
	public Boolean isFinished() {
		return sumOfRolls().equals(MAX_NUMBER_PINS_IN_ONE_FRAME) || (rolls.size() == MAX_NUMBER_OF_ROLLS);
	}

	public BONUS getBonus() {
		return bonus;
	}

	public Integer getFirstRollIfExistsOtherReturnZero() {
		if (rolls.size() > 0) {
			return rolls.get(0);
		}
		return 0;
	}

	public Integer getSecondRollIfExistsOtherReturnZero() {
		if (rolls.size() > 1) {
			return rolls.get(1);
		}
		return 0;
	}
	
	public void setNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}
	
	@Override
	public Frame getNextFrame() {
		return nextFrame;
	}

}
