package me.scrtv.assets;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import me.scrtv.main.BaseClock;
import me.scrtv.main.Main;
import me.scrtv.utils.Utils;

public class HUDManager implements MouseListener {
	private static JLabel score;
	private static JPanel fail;
	private static JLabel restart = new JLabel(new ImageIcon("res/imgs/reload.png"));
	
	public static void init() {
		score = new JLabel();
		score.setText(Utils.parseInt(0));
		score.setForeground(Color.LIGHT_GRAY);
		Main.mainframe.add(score);
		score.setBounds(15, 15, 100, 50);
		score.setFont(new Font(Fonts.useFont("achgrotesque").getName(), Font.BOLD, 50));
		fail = new JPanel();
		
		//JButton restart = new JButton("retry");
		//fail.add(restart);
		
		fail.setBackground(new Color(0.5f, 0.5f, 0.5f, 0.5f));
		fail.setVisible(false);
		restart.addMouseListener(new HUDManager());
		fail.add(restart);
		
		Main.mainframe.add(fail);
		fail.setBounds(0, 0, Main.mainframe.getWidth(), Main.mainframe.getHeight());
	}
	public static void changeScore(int ns) { // new score
		score.setText(Utils.parseInt(ns));
	}
	public static void loose() {
		Main.clock.cancel();
		System.out.println("loose @ " + Main.getScore());
		Main.mainframe.removeKeyListener(Main.keys);
		fail.setVisible(true);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// restart
		fail.setVisible(false);
		Main.clearScore();
		//Main.pX.restart();
		BaseClock.start();
		Main.mainframe.addKeyListener(Main.keys);
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}