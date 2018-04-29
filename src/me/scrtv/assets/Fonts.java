package me.scrtv.assets;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import me.scrtv.utils.Utils;

public class Fonts {
	private static HashMap<String, Font> fonts = new HashMap<String, Font>();
	
	public static Font useFont(String name) {
		if(fonts.containsKey(name.toLowerCase().replace(" ", "")))
			return fonts.get(name);
		System.out.println("not found, listed:");
		for(Entry<String, Font> fs : fonts.entrySet()) {
			System.out.println(" - " + fs.getKey());
		}
		return null;
	}
	
	public static boolean registerFont(File f, String name) {
		Font fn;
		try {
			fn = Font.createFont(Font.TRUETYPE_FONT, f);
//			if(!GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fn)) {
//				return false;		we don't need this as everytime we are using the font, it's accessing the original resource
//			} else {
//				System.out.println("bp");
				fonts.put(name.toLowerCase().replace(" ", ""), fn);
				return true;
//			}
		} catch (FontFormatException | IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static boolean registerFont(File f) {
		return registerFont(f, Utils.removeExt(f.getName().toLowerCase()));
	}
	public static boolean registerFont(String file, String name) {
		return registerFont(new File(file));
	}
	public static boolean registerFont(String file) {
		return registerFont(new File(file), Utils.removeExt(new File(file).getName().toLowerCase()));
	}
}