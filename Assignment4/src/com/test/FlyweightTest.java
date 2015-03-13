package com.test;

import static org.junit.Assert.assertTrue;

import java.awt.Font;
import java.io.IOException;

import org.junit.Test;

import com.document.CharacterFactory;
import com.document.CharacterFlyweight;
import com.document.CharacterFont;
import com.document.FontFactory;
import com.document.RunArray;

public class FlyweightTest {

	@Test
	public void testForComparingFlyweightEffect() throws IOException {
		double memoryOccupiedByNonFlyweight = (new SizeofUtil() {
			@Override
			protected int create() {
				CharacterFont[] nonFlyweight = new CharacterFont[326];
				for (int i = 1; i <= 115; i++) {
					nonFlyweight[i - 1] = new CharacterFont(new Character('C'), new Font("Calibri", Font.BOLD, 10));
				}

				for (int i = 116; i <= 326; i++) {
					nonFlyweight[i - 1] = new CharacterFont(new Character('C'), new Font("Calibri", Font.PLAIN, 10));
				}
				return 1;
			}
		}.averageBytes());
		double memoryOccupiedByFlyweight = new SizeofUtil() {
			@Override
			protected int create() {
				CharacterFlyweight[] charArray = new CharacterFlyweight[326];
				CharacterFactory factory = CharacterFactory.getInstance();
				// Entering 54 distinct characters
				for (int i = 1; i <= 54; i++) {
					charArray[i - 1] = factory.getFlyweight(new Character((char) i));
				}
				// Entering shared character
				for (int i = 55; i <= 326; i++) {
					charArray[i - 1] = factory.getFlyweight(new Character('`'));
				}
				RunArray runArray = new RunArray();
				FontFactory fontFactory = FontFactory.getInstance();
				runArray.addRun(1, 1, fontFactory.getFlyweight("Calibri", Font.BOLD, 10));
				for (int i = 1; i <= 54; i++) {
					fontFactory.getFlyweight("Calibri", Font.BOLD, 10);
				}
				for (int i = 55; i <= 326; i++) {
					fontFactory.getFlyweight("Calibri", Font.PLAIN, 10);
				}
				runArray.addRun(1, 115, fontFactory.getFlyweight("Calibri", Font.BOLD, 10));
				runArray.appendRun(272, fontFactory.getFlyweight("Calibri", Font.PLAIN, 10));
				return 1;
			}
		}.averageBytes();

		assertTrue(memoryOccupiedByNonFlyweight > memoryOccupiedByFlyweight);
		assertTrue(memoryOccupiedByNonFlyweight == 37832.0);
		assertTrue(memoryOccupiedByFlyweight == 17264.0);
	}

	@Test
	public void testRunArray() {
		RunArray test = new RunArray();
		Font fontA = new Font("Calibri", Font.BOLD, 10);
		Font fontB = new Font("Calibri", Font.BOLD, 20);
		test.addRun(0, 250, fontA);
		test.addRun(250, 10, fontB);
		test.appendRun(320, fontA);
		test.addRun(10, 150, fontB);
		assertTrue(test.getFont(5).equals(fontA));
		assertTrue(test.getFont(20).equals(fontB));
		assertTrue(test.getFont(200).equals(fontA));
		assertTrue(test.getFont(300).equals(fontA));
	}
}
