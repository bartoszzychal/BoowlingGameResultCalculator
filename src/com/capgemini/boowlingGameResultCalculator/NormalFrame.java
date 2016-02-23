package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public class NormalFrame extends AbstractFrame {

    private final int MAX_NUMBER_OF_ROLLS = 2;
    private final int MAX_NUMBER_PINS_IN_ONE_NORMAL_FRAME = 10;

    public NormalFrame() {
        rolls = new ArrayList<>(MAX_NUMBER_OF_ROLLS);
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
