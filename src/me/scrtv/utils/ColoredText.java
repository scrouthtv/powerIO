package me.scrtv.utils;

import java.awt.Color;
import java.awt.Font;

// TODO any class with <T> ??

public class ColoredText {
	private String txt;
	private Color c;
	private Font f;
	private boolean centered;
	
	public ColoredText(String txt, Color c, Font f, boolean centered) {
		this.txt = txt;
		this.c = c;
		this.f = f;
		this.centered = centered;
	}

	public String getText() {
		return txt;
	}

	public Color getColor() {
		return c;
	}
	
	public Font getFont() {
		return f;
	}
	public boolean isCentered() {
		return centered;
	}
}