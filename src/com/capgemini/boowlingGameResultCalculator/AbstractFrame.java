package com.capgemini.boowlingGameResultCalculator;

import java.util.List;

public abstract class AbstractFrame implements Frame {

    protected final int MIN_NUMBER_PINS_IN_ONE_ROLL = 0;
    protected final int MAX_NUMBER_PINS_IN_ONE_ROLL = 10;

    protected List<Integer> rolls;
    protected Frame nextFrame;

    public abstract void setNextFrame(Frame frame);

    public abstract Integer score();

    public abstract Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame);
    
    public abstract Boolean isFinished();

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
                    "Number of pins in two rolls can not be bigger then " + MAX_NUMBER_PINS_IN_ONE_ROLL);
        }
        rolls.add(numberOfPins);
    }
    
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
