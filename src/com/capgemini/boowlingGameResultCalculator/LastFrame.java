package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class LastFrame extends AbstractFrame {

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
    protected Boolean isStrike() {
        return rolls.get(0) == MAX_NUMBER_PINS_IN_ONE_ROLL;
    }

    @Override
    public Boolean isFinished() {
        return !isSpare() && !isStrike() && rolls.size() == MIN_NUMBER_OF_ROLLS || rolls.size() == MAX_NUMBER_OF_ROLLS;
    }

    @Override
    public Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame) {
        return getFirstRoll() + getSecondRollIfExistsOtherReturnZero();
    }

    @Override
    public void setNextFrame(Frame frame){
        try {
			throw new IllegalAccessException("Last frame can not have next frame");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }

}
