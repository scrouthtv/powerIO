package me.scrtv.assets;

import javax.swing.JLabel;

public class HUD {
	// TODO write as object instances not static variabels ?!
	
	private static JLabel score = new JLabel();
	
	public static JLabel getScore() {
		reloadScore();
		return score;
	}
	
	private static void reloadScore() {
		score.setText("asdfasdfasdf");
	}
	
	private static void initializeScore() {
		score.setBackground(Colors.TRANSPARENT.use());
		score.setText("SCORE");
	}
	public static void initializeHUD() {
		initializeScore();
	}
}