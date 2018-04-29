package me.scrtv.assets;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import me.scrtv.main.Main;
import me.scrtv.utils.Utils;

public class HUDManager {
	private static JLabel score;
	private static JPanel fail;
	
	public static void init() {
		score = new JLabel();
		score.setText(Utils.parseInt(0));
		Main.mainframe.add(score);
		score.setBounds(0, 0, 100, 50);
		score.setFont(new Font(Fonts.useFont("achgrotesque").getName(), Font.BOLD, 50));
		fail = new JPanel();
		
		//JButton restart = new JButton("retry");
		//fail.add(restart);
		
		fail.setBackground(new Color(0.5f, 0.5f, 0.5f, 0.5f));
		fail.setVisible(false);
		Main.mainframe.add(fail);
		fail.setBounds(0, 0, Main.mainframe.getWidth(), Main.mainframe.getHeight());
	}
	public static void changeScore(int ns) { // new score
		score.setText(Utils.parseInt(ns));
	}
	public static void loose() {
		Main.clock.cancel();
		System.out.println("you loose");
		Main.mainframe.removeKeyListener(Main.keys);
	}
}