package com.document;

import java.awt.Font;
import java.util.Map;
import java.util.TreeMap;

public class RunArray {
	private TreeMap<Integer, Font> fontMap = new TreeMap<Integer, Font>();

	public void addRun(int begin, int end, Font font) {
		if (fontMap.isEmpty()) {
			fontMap.put(end, font);
			return;
		}
		if (fontMap.lastKey() > begin) {
			// If changing font of existing characters
			Map.Entry<Integer, Font> precederOfBegin = fontMap.floorEntry(begin);
			Map.Entry<Integer, Font> precederOfEnd = fontMap.floorEntry(end);
			if(precederOfBegin==null && precederOfEnd == null){
				int successorOfBegin = fontMap.ceilingKey(begin);
				fontMap.put(begin - 1, fontMap.get(successorOfBegin));
				fontMap.put(end, font);
			}else if(precederOfBegin==null){
				int successorOfBegin = fontMap.ceilingKey(begin);
				fontMap.put(begin - 1, fontMap.get(successorOfBegin));
				fontMap.remove(fontMap.ceilingKey(begin));
				fontMap.put(end, font);
			}else 
			if (precederOfEnd ==null ||precederOfBegin.getKey() == precederOfEnd.getKey()) {
				if(isSameFont(precederOfBegin.getKey(),font)){
					fontMap.remove(precederOfBegin.getKey());
				}else if(isSameFont(precederOfEnd.getKey(), font)){
					return;
				}else{
					int successorOfEnd = fontMap.ceilingKey(end);
					fontMap.put(begin - 1, fontMap.get(successorOfEnd));
				}
				fontMap.put(end, font);
			} else {
				if(fontMap.containsKey(begin)){
					Font oldBeginFont=fontMap.get(begin);
					fontMap.put(begin-1, oldBeginFont);
				}else{
					Map.Entry<Integer, Font> succeederOfBegin = fontMap.ceilingEntry(begin);
					fontMap.put(begin-1, succeederOfBegin.getValue());
				}
				fontMap.put(end, font);
				fontMap.subMap(begin, end).clear();
			}
			fontMap.put(end, font);
		}else{
			appendRun(end, font);
		}
	}

	private boolean isSameFont(Integer key, Font font) {
		Font existingFont = fontMap.get(key);
		return (existingFont.getFamily().equals(font.getFamily()) && existingFont.getStyle() == font.getStyle() && existingFont
				.getSize() == font.getSize());
	}

	public void appendRun(int end, Font font) {
		int newLastIndex = fontMap.lastKey() + end;
		if (isSameFont(fontMap.lastKey(), font)) {
			fontMap.remove(fontMap.lastKey());
		}
		fontMap.put(newLastIndex, font);
	}
	
	public Font getFont(int key){
		return fontMap.ceilingEntry(key).getValue();
	}
}
