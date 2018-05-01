package me.scrtv.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import me.scrtv.assets.Characters;
import me.scrtv.assets.Fonts;
import me.scrtv.assets.HUDManager;
import me.scrtv.assets.Pins;
import me.scrtv.utils.CalcUtils;
import me.scrtv.utils.PixelPanel;
import me.scrtv.utils.Utils;

public class Main {
	public static JFrame mainframe = new JFrame();
	public static PixelPanel pchar; // players character
	public static int mainheight, mainwidth, ppb;
	public static BaseClock clock;
	public static final Physics pX = new Physics(); // main physics
	public static int cSP = 1; // what the tank will destroy in one second TODO add some randomness to obstacle hp (e. g. 1 % +-)
	public static final int maxobsthp = 10; // must be multiplied with cSP => this var is max power
	public static final boolean loosable = true;
	public static KeyListener keys;
	public static double mvMulti = 1.0; // can be lowered to slow the game down / etc.
	private static int score = 0;
	
	public static void main(String[] args) {
		new Font(Font.DIALOG_INPUT, 0, 0);
		Dimension d = CalcUtils.calcMainFrameDimensions();
		mainheight = d.height;
		mainwidth = d.width;
		mainframe.setSize(d);
		mainframe.setLocation(CalcUtils.calcMainFramePosition());
		mainframe.getContentPane().setBackground(Color.DARK_GRAY);
		mainframe.setLayout(null);
		mainframe.setVisible(true);
		mainframe.setTitle("power.io");
		mainframe.setResizable(false);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pchar = Characters.basiccharacter(3);
		
		Fonts.registerFont("res/fonts/CHILLER.ttf");
		Fonts.registerFont("res/fonts/AchGrotesque.otf");
		
		// TODO HUD
		HUDManager.init();
		
		keys = new Movement(pchar);
		mainframe.addKeyListener(keys);
		mainframe.add(pchar);
		pchar.setBounds(135, mainheight - pchar.getPreferredSize().height - ppb, pchar.getPreferredSize().width, pchar.getPreferredSize().height);
		
		BaseClock.start();
	}
	public static void shoot() {
		int n = pchar.getProperty("sn");
		for(int i = 0; i < n; i++) {
			PixelPanel pin = Pins.basicpin(1, 1);
			int posx = pchar.getX() + (pchar.getProperty("s" + i + "x") - 1) * pchar.getScale() + pchar.getScale() / 2;
			int posy = (int) (pchar.getY() + (pchar.getProperty("s" + i + "y") - 1) * pchar.getScale() + pchar.getScale() / 2 - pin.getPreferredSize().getHeight()); // TODO loose screen not spawning?
			pX.spawnPin(Pins.basicpin(cSP, 1), posx, posy);
		}
	}
	public static String getScore() {
		return Utils.parseInt(score);
	}
	public static int getIScore() {
		return score;
	}
	public static void addScore(int plus) {
		Main.score += plus;
		HUDManager.changeScore(Main.score);
	}
	public static void clearScore() {
		Main.score = 0;
		HUDManager.changeScore(Main.score);
	}
}