package me.scrtv.assets;

import java.awt.Color;

import me.scrtv.main.Main;
import me.scrtv.utils.PixelPanel;

public class Characters {
	static int w = 7;
	static int h = 12;
	static int scale = Main.ppb / w;
	
	public static PixelPanel basiccharacter(int id) {
		PixelPanel pchar = new PixelPanel(w, h, scale);
		//usable: everything from
		// pchar.drawPixel(0, 0, Color.CYAN);
		//to
		// pchar.drawPixel(6, 11, Color.GREEN);
		
		switch(id) {
			case 3:
				pchar.setLRSymetry(true);
				
				for(int n = 2; n <= 4; n++) {
					pchar.drawPixel(2, n, Color.BLUE);
					pchar.drawPixel(4, n, Color.BLUE);
					pchar.drawPixel(n, 2, Color.BLUE);
					pchar.drawPixel(n, 4, Color.BLUE);
				}
				for(int n = 1; n <= 5; n++) {
					pchar.drawPixel(1, n, Color.RED);
					pchar.drawPixel(5, n, Color.RED);
					pchar.drawPixel(n, 1, Color.RED);
					pchar.drawPixel(n, 5, Color.RED);
				}
				for(int n = 0; n <= 6; n++) {
					pchar.drawPixel(0, n, Color.GRAY);
					pchar.drawPixel(6, n, Color.GRAY);
					pchar.drawPixel(n, 0, Color.GRAY);
					pchar.drawPixel(n, 6, Color.GRAY);
				}
				pchar.drawPixel(2, 3, Color.YELLOW);
				pchar.drawPixel(3, 3, Color.YELLOW);
				pchar.drawPixel(4, 3, Color.YELLOW);
				pchar.drawPixel(3, 2, Color.YELLOW);
				pchar.drawPixel(3, 1, Color.YELLOW);
				pchar.setProperty("s0x", 3);
				pchar.setProperty("s0y", 3);
				pchar.setProperty("sn", 1);
		}
		
		return pchar;
	}
}