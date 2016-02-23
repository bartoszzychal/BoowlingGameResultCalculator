package com.capgemini.boowlingGameResultCalculator;

public interface Frame {

	/**
	 * Register a throw a ball 
	 * @param numberOfPins  number of knocked down pins
	 */
    void addRoll(int numberOfPins);

    /**
     * Register reference to next frame in current frame
     * @param frame next frame for current frame
     */
    void setNextFrame(Frame frame);

    /**
     * 
     * @return Current game score
     */
    Integer score();

    /**
     * Count a bonus for previous frame 
     * @param isStrikeInPreviousFrame information about type of bonus if is false bonus is count for spare if true for strike
     * @return bonus value for frame
     */
    Integer getBonusForPreviousFrame(Boolean isStrikeInPreviousFrame);

    /**
     * 
     * @return return value of hits pins in first roll current frame
     */
    Integer getFirstRoll();

    /**
     * 
     * @return true if current frame is finished other false
     */
    Boolean isFinished();

}