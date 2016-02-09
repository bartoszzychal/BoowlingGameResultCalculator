package com.capgemini.boowlingGameResultCalculator;

public interface BoowlingGameResultCalculatorInterface {
	/**
	 * Register a thrown a ball.
	 * @param numberOfPins number of knocked down pins
	 * @throws Exception 
	 */
	public void roll(int numberOfPins) throws Exception;

	/**
	 * @return current game score
	 */
	public int score();

	/**
	 * @return true if a game is over, otherwise false
	 */
	public boolean isFinished();
}
