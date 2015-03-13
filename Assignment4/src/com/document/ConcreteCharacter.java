package com.document;

import java.awt.Graphics;

public class ConcreteCharacter implements CharacterFlyweight {

	private int codepoint;

	public int getCodepoint() {
		return codepoint;
	}

	public ConcreteCharacter(Character aChar) {
		codepoint = (int) aChar;
	}

	@Override
	public void printOn(Graphics g) {
		// Will be implemented in future
	}
}
