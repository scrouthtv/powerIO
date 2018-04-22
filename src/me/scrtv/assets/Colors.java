package me.scrtv.assets;

import java.awt.Color;

public enum Colors {
	// define by vals:  red   green blue alpha 
	DARK_GREY(new Color(0.2f, 0.2f, 0.2f, 1.0f)),
	WHITE(new Color(0.8f, 0.8f, 0.8f, 1.0f)),
	RED(new Color(0.2f, 0.8f, 0.8f, 1.0f)),
	TRANSPARENT(new Color(0.0f, 0.0f, 0.0f, 0.0f)); // *no* alpha is transparent
	
	private Color c;
	private Colors(Color c) {
		this.c = c;
	}
	public Color use() {
		return c;
	}
}