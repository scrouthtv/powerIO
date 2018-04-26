package me.scrtv.assets;

import java.awt.Font;
import java.io.File;
import java.util.HashMap;

public class Fonts {
	/*public static Font chiller;
	
	public static void mfga() {
		try {
			chiller = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Megrim.ttf"));
			if(!GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(chiller)) // dunno whether we need this TODO further testing
				System.out.println("Chiller could not be loaded.");
			else
				System.out.println("Chiller was loaded.");
		} catch (FontFormatException | IOException | NullPointerException ex) {
			System.out.println("Chiller could not be loaded: " + ex.getMessage());
		}
	}*/
	
	private static HashMap<String, Font> fonts = new HashMap<String, Font>();
	
	public static Font useFont() {
		if(fonts.contains(arg0))
	}
	
	public static boolean registerFont(File f, String name) {
		
	}
	public static boolean registerFont(File f) {
		return registerFont(f, f.getName().toLowerCase());
	}
	public static boolean registerFont(String file, String name) {
		return registerFont(new File(file));
	}
	public static boolean registerFont(String file) {
		return registerFont(new File(file), new File(file).getName().toLowerCase());
	}
}