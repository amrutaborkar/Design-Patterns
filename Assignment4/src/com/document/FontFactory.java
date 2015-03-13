package com.document;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class FontFactory {

	List<Font> usedFonts;

	private static FontFactory instance;

	private FontFactory() {
	}

	public static synchronized FontFactory getInstance() {
		if (instance == null) {
			instance = new FontFactory();
		}
		return instance;
	}

	public Font getFlyweight(String fontName, int fontStyle, int pointSize) {
		if (usedFonts == null) {
			usedFonts = new ArrayList<Font>();
		}
		for (Font font : usedFonts) {
			if (font.getFamily().equals(fontName) && font.getStyle() == fontStyle && font.getSize() == pointSize) {
				return font;
			}
		}
		Font newFont = new Font(fontName, fontStyle, pointSize);
		usedFonts.add(newFont);
		return newFont;
	}
}
