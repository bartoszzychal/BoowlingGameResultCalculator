package com.capgemini.boowlingGameResultCalculator;

public class FrameFactory {
	private final int MAX_NUMBER_FRAMES = 10;
	private int counter= 0;
	
	Frame getFrame(){
		counter++;
		if(counter == MAX_NUMBER_FRAMES){
			return new LastFrame();
		}
		return new NormalFrame();
	}
}
