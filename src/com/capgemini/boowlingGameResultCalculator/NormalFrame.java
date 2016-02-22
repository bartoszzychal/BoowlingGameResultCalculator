package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class NormalFrame extends AbstractFrame {

    private final int MAX_NUMBER_OF_ROLLS = 2;
    private final int MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME = 10;

    public NormalFrame() {
        rolls = new ArrayList<>(MAX_NUMBER_OF_ROLLS);
    }

    // REVIEW bzychal - duplicated method here and in the LastFrame, the implementation in the LastFrame has a bit
    // different check for the third case, but it should be checked here as well. Please move
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
            // REVIEW bzychal - not tested
            throw new IllegalArgumentException(
                    "Number of pins in one frame can not be bigger then " + MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME);
        }

        rolls.add(numberOfPins);
    }

    @Override
    public Integer score() {
        return sumOfRolls() + getBonusValue();
    }

    private Integer getBonusValue() {
        Integer bonusValue = Integer.valueOf(0);
        if (isSpare() || isStrike()) {
            bonusValue = nextFrame.getBonusForPreviousFrame(isStrike());
        }
        return bonusValue;
    }

    @Override
    protected Boolean isStrike() {
        return sumOfRolls().equals(MAX_NUMBER_PINS_IN_ONE_ROLL) && rolls.size() == 1;
    }

    @Override
    public Boolean isFinished() {
        return sumOfRolls().equals(MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME) || (rolls.size() == MAX_NUMBER_OF_ROLLS);
    }

    @Override
    public Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame) {
        Integer bonus = getFirstRoll();
        if (isStrike() && isStrikeInPreviousFrame && nextFrame != null) {
            bonus += nextFrame.getFirstRoll();
        }
        else if (!isStrike() && isStrikeInPreviousFrame) {
            bonus += getSecondRollIfExistsOtherReturnZero();
        }
        return bonus;
    };

    @Override
    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

}
