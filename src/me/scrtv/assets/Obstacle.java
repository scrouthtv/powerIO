package me.scrtv.assets;

import java.awt.Color;
import java.awt.Font;

import me.scrtv.main.Main;
import me.scrtv.utils.ColorRamp;
import me.scrtv.utils.PixelPanel;
import me.scrtv.utils.Utils;

public class Obstacle {
	// power is how many shots it can take
	public static PixelPanel obstacle(int power) {
		power = Math.min(Math.max(power, 0), Main.maxobsthp);
		int hp = power * Main.cSP;
		// System.out.println("obstacle with " + hp);
		PixelPanel pobst = new PixelPanel(1, 1, Main.ppb);
		pobst.drawPixel(0, 0, ColorRamp.ryg.use((double) (Main.maxobsthp - power) / (double)Main.maxobsthp));
		// TODO color per time it takes to destroy the block
		pobst.setProperty("strength", hp);
		pobst.rotate(45);
		pobst.addOverlay(Utils.parseInt(hp), 0, 0, Color.BLUE, new Font(Fonts.useFont("chiller").getName(), Font.BOLD, Main.ppb / 2), true);
		return pobst;
	}
}