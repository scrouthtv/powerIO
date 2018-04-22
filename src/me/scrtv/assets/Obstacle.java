package me.scrtv.assets;

import java.awt.Color;
import java.awt.Font;

import me.scrtv.main.Main;
import me.scrtv.utils.PixelPanel;
import me.scrtv.utils.Utils;

public class Obstacle {
	// power is how many shots it can take
	public static PixelPanel obstacle(int power) {
		power = Math.min(Math.max(power, 0), Main.maxobsthp);
		int hp = power * Main.cSP;
		System.out.println("obstacle with " + hp);
		PixelPanel pobst = new PixelPanel(1, 1, Main.ppb);
		pobst.drawPixel(0, 0, Utils.cramp(power));
		// TODO color per time it takes to destroy the block
		pobst.setProperty("strength", power);
		pobst.addOverlay(Utils.parseInt(power), 0, 0, Color.BLUE, new Font(Font.DIALOG_INPUT, Font.PLAIN, Main.ppb / 2), true);
		return pobst;
	}
}