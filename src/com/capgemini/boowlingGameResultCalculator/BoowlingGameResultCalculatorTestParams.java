package com.capgemini.boowlingGameResultCalculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BoowlingGameResultCalculatorTestParams {

	BoowlingGameResultCalculator boowlingGameResultCalculator;

	enum WithException{
		YES,NO
	};

	enum OnlyLastFrame{
		YES,NO
	};
	
	private WithException withException;
	private OnlyLastFrame onlyLastFrame;
	private List<Integer> list;
	private int expected;
	
	public BoowlingGameResultCalculatorTestParams(WithException status,OnlyLastFrame lastFrame,List<Integer> list, int expected) {
		this.withException = status;
		this.onlyLastFrame = lastFrame;
		this.list = list;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(0),0},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(4),4},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(1,1),2},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(9,1,9),28},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(10,8,1),28},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(10,10,5),45},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(10,10,10,10,10,10,10,10,10,10),270},
			{WithException.NO,OnlyLastFrame.NO, Arrays.asList(10,10,10,10,10,10,10,10,10,10,10,10),300},
			
			{WithException.NO,OnlyLastFrame.YES, Arrays.asList(4,5),9},
			{WithException.NO,OnlyLastFrame.YES, Arrays.asList(10,5,4),19},
			{WithException.NO,OnlyLastFrame.YES, Arrays.asList(9,1,2),12},
			
			{WithException.YES,OnlyLastFrame.NO, Arrays.asList(-1),0},
			{WithException.YES,OnlyLastFrame.YES, Arrays.asList(-1),0},
			
			{WithException.YES,OnlyLastFrame.NO, Arrays.asList(11),0},
			{WithException.YES,OnlyLastFrame.YES, Arrays.asList(11),0},
			
			{WithException.YES,OnlyLastFrame.NO, Arrays.asList(10,11),0},
			{WithException.YES,OnlyLastFrame.YES, Arrays.asList(10,10,11),0},
			
			{WithException.YES,OnlyLastFrame.NO, Arrays.asList(6,6),0},
			{WithException.YES,OnlyLastFrame.YES, Arrays.asList(6,6,6),0},
		});
	}

	@Before
	public void beforeTestCreateObject() {
		boowlingGameResultCalculator = new BoowlingGameResultCalculator();
	}

	@After
	public void afterTestDeleteObject() {
		boowlingGameResultCalculator = null;
	}


	@Test
	public void shouldBeExpectedForParametersWithoutException() {
		//when
		if(WithException.NO.equals(withException)){
			 
			if(OnlyLastFrame.YES.equals(onlyLastFrame)){
				int zero = 0;
				for (int i = 0; i < 9; i++) {
					boowlingGameResultCalculator.roll(zero);
					boowlingGameResultCalculator.roll(zero);
				}
			}
			
			for (Integer pins : list) {
				boowlingGameResultCalculator.roll(pins);
			}
			
			int score = boowlingGameResultCalculator.score();
			
			//then
			assertEquals(expected, score);
			
		}
		

	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldBeThrowIllegalArgumentException() {
		 //when
		if(WithException.YES.equals(withException)){
			
			if(OnlyLastFrame.YES.equals(onlyLastFrame)){
				int zero = 0;
				for (int i = 0; i < 9; i++) {
					boowlingGameResultCalculator.roll(zero);
					boowlingGameResultCalculator.roll(zero);
				}
			}
			
			//then
			thrown.expect(IllegalArgumentException.class);
			for (Integer pins : list) {
				boowlingGameResultCalculator.roll(pins);
			}	
		}
	}
}
