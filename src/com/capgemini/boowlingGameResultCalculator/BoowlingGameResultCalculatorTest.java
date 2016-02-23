package com.capgemini.boowlingGameResultCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoowlingGameResultCalculatorTest {

	BoowlingGameResultCalculatorInterface boowlingGameResultCalculator;

	@Before
	public void beforeTestCreateObject() {
		boowlingGameResultCalculator = new BoowlingGameResultCalculator();
	}

	@After
	public void afterTestDeleteObject() {
		boowlingGameResultCalculator = null;
	}

	@Test
	public void shouldBeZeroForHitsZeroPins() throws Exception {
		// given
		int zeroPins = 0;
		// when
		boowlingGameResultCalculator.roll(zeroPins);
		int score = boowlingGameResultCalculator.score();
		// then
		assertEquals(0, score);
	}
	@Test
	public void shouldBeZeroForNotThrows() {
		// given
		// when
		int score = boowlingGameResultCalculator.score();
		// then
		assertEquals(0, score);
	}

	@Test
	public void shouldBeFourForHitsFourPins() throws Exception {
		// given
		int fourPins = 4;
		// when
		boowlingGameResultCalculator.roll(fourPins);
		int score = boowlingGameResultCalculator.score();
		// then
		assertEquals(4, score);
	}

	@Test
	public void shouldBeTwoForHitsOnePinInFristHitAndOnePinInSecondHit() throws Exception {
		// given
		int onePinInFirstHit = 1;
		int onePinInSecondHit = 1;
		// when
		boowlingGameResultCalculator.roll(onePinInFirstHit);
		boowlingGameResultCalculator.roll(onePinInSecondHit);

		int score = boowlingGameResultCalculator.score();
		// then
		assertEquals(onePinInFirstHit + onePinInSecondHit, score);
	}

	@Test
	public void shouldBeTwentyEightForSpareAndHitNinePinsInFirstThrowNextRound() throws Exception {
		// given
		// spare
		int firstNumberOfPinsInSpare = 9;
		int secondNumberOfPinsInSpare = 1;
		// next round
		int firstNumberOfPinsThrowInNextRound = 9;

		// when
		boowlingGameResultCalculator.roll(firstNumberOfPinsInSpare);
		boowlingGameResultCalculator.roll(secondNumberOfPinsInSpare);
		boowlingGameResultCalculator.roll(firstNumberOfPinsThrowInNextRound);

		int score = boowlingGameResultCalculator.score();

		// then
		assertEquals(firstNumberOfPinsInSpare + secondNumberOfPinsInSpare + firstNumberOfPinsThrowInNextRound
				+ firstNumberOfPinsThrowInNextRound, score);
	}

	@Test
	public void shouldBeTwentyEightForStrikeAndHitEightAndOneInNextFrame() throws Exception {
		// given
		// strike
		int strike = 10;
		// next round
		int fistNextFrame = 8;
		int secondNextFrame = 1;

		// when
		boowlingGameResultCalculator.roll(strike);
		boowlingGameResultCalculator.roll(fistNextFrame);
		boowlingGameResultCalculator.roll(secondNextFrame);

		int score = boowlingGameResultCalculator.score();

		// then
		assertEquals(strike + fistNextFrame + secondNextFrame + fistNextFrame + secondNextFrame, score);
	}

	@Test
	public void shouldBeTFourtyFiveForTenTenFive() throws Exception {
		// given
		int firstRoll = 10;
		int secondRoll = 10;
		int thirdRoll = 5;

		// when
		boowlingGameResultCalculator.roll(firstRoll);
		boowlingGameResultCalculator.roll(secondRoll);
		boowlingGameResultCalculator.roll(thirdRoll);

		int score = boowlingGameResultCalculator.score();

		// then
		assertEquals(45, score);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldBeExceptionForNegativeNumber() throws Exception {
		// given
		int firstRoll = -1;

		// when
		boowlingGameResultCalculator.roll(firstRoll);

		// then
		boowlingGameResultCalculator.score();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldBeExceptionForEleven() throws Exception {
		// given
		int firstRoll = 11;

		// when
		boowlingGameResultCalculator.roll(firstRoll);
		// then
		boowlingGameResultCalculator.score();
	}

	@Test
	public void shouldBeNineTeenForOnlyZerosInNormalFramesAndFiveAndFourInLastFrame() throws Exception {
		// given
		int zero = 0;
		int five = 5;
		int four = 4;

		// when
		for (int normalFrames = 0; normalFrames < 9; normalFrames++) {
			boowlingGameResultCalculator.roll(zero);
			boowlingGameResultCalculator.roll(zero);
		}

		boowlingGameResultCalculator.roll(four);
		boowlingGameResultCalculator.roll(five);

		int score = boowlingGameResultCalculator.score();

		// then
		assertEquals(9, score);
	}

	@Test
	public void shouldBeNineTeenForOnlyZerosInNormalFramesAndTenFiveFour() throws Exception {
		// given
		int zero = 0;
		int ten = 10;
		int five = 5;
		int four = 4;

		// when
		for (int normalFrames = 0; normalFrames < 9; normalFrames++) {
			boowlingGameResultCalculator.roll(zero);
			boowlingGameResultCalculator.roll(zero);
		}

		boowlingGameResultCalculator.roll(ten);
		boowlingGameResultCalculator.roll(five);
		boowlingGameResultCalculator.roll(four);

		int score = boowlingGameResultCalculator.score();

		// then
		assertEquals(19, score);
	}

	@Test
	public void shouldBeTwelveForOnlyZerosInNormalFramesAndNineOneTwo() throws Exception {
		// given
		int zero = 0;
		int nine = 9;
		int one = 1;
		int two = 2;

		// when
		for (int normalFrames = 1; normalFrames <= 9; normalFrames++) {
			boowlingGameResultCalculator.roll(zero);
			boowlingGameResultCalculator.roll(zero);
		}

		boowlingGameResultCalculator.roll(nine);
		boowlingGameResultCalculator.roll(one);
		boowlingGameResultCalculator.roll(two);

		int score = boowlingGameResultCalculator.score();

		// then
		assertEquals(12, score);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldBeExceptionOnlyZerosInNormalFramesAndTenTenEleven() throws Exception {
		// given
		int ten = 10;
		int eleven = 11;
		int zero = 0;

		// when
		for (int normalFrames = 0; normalFrames < 9; normalFrames++) {
			boowlingGameResultCalculator.roll(zero);
			boowlingGameResultCalculator.roll(zero);
		}

		boowlingGameResultCalculator.roll(ten);
		boowlingGameResultCalculator.roll(ten);
		boowlingGameResultCalculator.roll(eleven);

		boowlingGameResultCalculator.score();

	}



	@Test
	public void shouldBe300ForAllStrike() throws Exception {
		// given
		int ten = 10;

		// when
		for (int frames = 0; frames < 10; frames++) {
			boowlingGameResultCalculator.roll(ten);
		}

		boowlingGameResultCalculator.roll(ten);
		boowlingGameResultCalculator.roll(ten);

		int score = boowlingGameResultCalculator.score();
		
		assertEquals(300, score);
	}
	
	@Test(expected= IllegalAccessException.class)
	public void shouldIllegalAccesExceptionForGameFinishedAndNextRoll() throws Exception {
		// given
		int ten = 10;
		
		// when
		for (int frames = 0; frames < 10; frames++) {
			boowlingGameResultCalculator.roll(ten);
		}
		
		boowlingGameResultCalculator.roll(ten);
		boowlingGameResultCalculator.roll(ten);

		boowlingGameResultCalculator.roll(ten);

	}
	@Test(expected= IllegalArgumentException.class)
	public void shouldIllegalArgumentExceptionForNegativeNumber() throws Exception {
		// given
		int negativeNumber = -1;
		boowlingGameResultCalculator.roll(negativeNumber);
		
	}
	@Test(expected= IllegalArgumentException.class)
	public void shouldIllegalArgumentExceptionForNegativeNumberInLastFrame() throws Exception {
		// given
		int ten = 10;
		int negativeNumber = -1;

		// when
		for (int frames = 0; frames < 10; frames++) {
			boowlingGameResultCalculator.roll(ten);
		}
		
		boowlingGameResultCalculator.roll(negativeNumber);
		
	}
	@Test(expected= IllegalArgumentException.class)
	public void shouldIllegalArgumentException2And9InOneFrame() throws Exception {
		// given
		int two = 2;
		int nine = 9;
		
		// when
		boowlingGameResultCalculator.roll(two);
		boowlingGameResultCalculator.roll(nine);
	}
	@Test(expected= IllegalArgumentException.class)
	public void shouldIllegalArgumentException10And10and11InLastFrame() throws Exception {
		// given
		int ten = 10;
		int elf = 11;
		
		// when
		for (int frames = 0; frames < 10; frames++) {
			boowlingGameResultCalculator.roll(ten);
		}
		
		boowlingGameResultCalculator.roll(ten);
		boowlingGameResultCalculator.roll(elf);
	}
	
}
