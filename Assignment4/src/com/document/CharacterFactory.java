package com.document;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

	Map<Integer, CharacterFlyweight> usedCharacters = new HashMap<Integer, CharacterFlyweight>();

	private static CharacterFactory instance;

	private CharacterFactory() {
	}

	public static synchronized CharacterFactory getInstance() {
		if (instance == null) {
			instance = new CharacterFactory();
		}
		return instance;
	}

	public CharacterFlyweight getFlyweight(Character aChar) {

		if (usedCharacters.containsKey((int) aChar)) {
			return usedCharacters.get(aChar);
		}
		CharacterFlyweight newFlyweight = new ConcreteCharacter(aChar);
		usedCharacters.put(((ConcreteCharacter) newFlyweight).getCodepoint(), newFlyweight);
		return newFlyweight;
	}
}
