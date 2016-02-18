package com.capgemini.boowlingGameResultCalculator;

// REVIEW bzychal - Missing comments for all class and public methods
public interface Frame {

    void addRoll(int numberOfPins);

    // REVIEW bzychal - IllegalAccessExcption is a runtime exception and should not be placed in throws declaration
    void setNextFrame(Frame frame) throws IllegalAccessException;

    Integer score();

    Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame);

    Integer getFirstRoll();

    Boolean isFinished();

}