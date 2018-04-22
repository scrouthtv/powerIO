package me.scrtv.assets;

import java.awt.Color;

import me.scrtv.utils.PixelPanel;

public class Pins {
	private static int w = 3;
	private static int h = 7;
	
	//will form a ball to this power
	public static PixelPanel basicpin(int power, int id) {
		int scale = (Math.max((int) Math.ceil(Math.log10(power)), 1)) * 8;
		PixelPanel pchar = new PixelPanel(w, h, scale);
		//usable: everything from
		// pchar.drawPixel(0, 0, Color.CYAN);
		//to
		// pchar.drawPixel(2, 6, Color.GREEN);
		
		pchar.setProperty("strength", power);
		
		switch(id) {
			case 1:
				for(int y = 1; y <= 5; y++) {
					pchar.drawPixel(0, y, Color.YELLOW);
					pchar.drawPixel(1, y, Color.RED);
					pchar.drawPixel(2, y, Color.YELLOW);
				}
				pchar.drawPixel(1, 0, Color.YELLOW);
				pchar.drawPixel(1, 6, Color.YELLOW);
				pchar.setProperty("mvX", 0); // x vector of movement
				pchar.setProperty("mvY", -15); // y vector of movement
				break;
			case 2:
				
				break;
		}
		
		return pchar;
	}
}