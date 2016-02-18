package com.capgemini.boowlingGameResultCalculator;

import java.util.ArrayList;

public abstract class AbstractFrame implements Frame {

    protected final int MIN_NUMBER_PINS_IN_ONE_ROLL = 0;
    protected final int MAX_NUMBER_PINS_IN_ONE_ROLL = 10;

    // REVIEW bzychal - please use List interface and not ArrayList
    protected ArrayList<Integer> rolls;
    protected Frame nextFrame;

    // REVIEW bzychal - the empty overriden methods are not necessary here and should be removed.
    @Override
    public abstract void addRoll(int numberOfPins);

    @Override
    public abstract void setNextFrame(Frame frame) throws IllegalAccessException;

    @Override
    public abstract Integer score();

    @Override
    public abstract Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame);

    @Override
    public abstract Boolean isFinished();

    @Override
    public Integer getFirstRoll() {
        return rolls.get(0);
    }

    protected Integer sumOfRolls() {
        Integer sum = Integer.valueOf(0);
        for (Integer pins : rolls) {
            sum += pins;
        }
        return sum;
    }

    protected Integer getSecondRollIfExistsOtherReturnZero() {
        return rolls.size() > 1 ? rolls.get(1) : 0;
    }

    protected abstract Boolean isStrike();

    protected Boolean isSpare() {
        return sumOfRolls().equals(MAX_NUMBER_PINS_IN_ONE_ROLL) && rolls.size() == 2;
    }
}
