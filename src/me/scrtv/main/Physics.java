package me.scrtv.main;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import me.scrtv.assets.Fonts;
import me.scrtv.assets.HUDManager;
import me.scrtv.assets.Obstacle;
import me.scrtv.utils.ColorRamp;
import me.scrtv.utils.PixelPanel;
import me.scrtv.utils.Side;
import me.scrtv.utils.Utils;

public class Physics implements Runnable {
	public static int tt = 0; // table top
	private List<PixelPanel> pins = new ArrayList<PixelPanel>();
	private List<PixelPanel> obsts = new ArrayList<PixelPanel>();
	// this will add the pin to the world at given location and give the physics control over it
	private int iterate = 1;
	public void spawnPin(PixelPanel p, int x, int y) {
		pins.add(p);
		Main.mainframe.add(p);
		p.setBounds(x, y, p.getPreferredSize().width, p.getPreferredSize().height);
		//System.out.println("added pin");
	}
	
	// this will add the obstacle to the world at position (1, 2, 3, 4, 5) and give the physics control over it
	public void spawnObst(PixelPanel p, int x) {
		obsts.add(p);
		Main.mainframe.add(p);
		p.setBounds((x - 1) * Main.ppb, 15, p.getBounds().width, p.getBounds().width);
	}
	
	// TODO remake as event handling?!
	public void run() {
		try {
			for(PixelPanel pin : pins) {
				pin.move(pin.getProperty("mvX"), pin.getProperty("mvY"), false);
				if(!pin.isOnMap()) {
					Main.mainframe.remove(pin);
					pins.remove(pin);
				}
			}
			for(PixelPanel obst : obsts) {
				obst.move(0, 1, false);
				if(!obst.isOnMap(new Side[] {Side.N})) {
					Main.mainframe.remove(obst);
					obsts.remove(obst);
				}
				List<PixelPanel> colliders = obst.collides(pins);
				for(PixelPanel collider : colliders) {
					Main.mainframe.remove(collider);
					pins.remove(collider);
					int str = collider.getProperty("strength"); // pin strength
					int cHP = obst.getProperty("strength"); // current obstacle hp
					if(str >= cHP) { // pin destroys the obstacle
						Main.addScore(str);
						obsts.remove(obst);
						Main.mainframe.remove(obst); // TODO debris all over the screen on destroy
					} else { // pin only damages it
						int strength = cHP - str;
						obst.setProperty("strength", strength);
						obst.clearOverlays();
						obst.addOverlay(Utils.parseInt(strength), 0, 0, Color.BLUE, new Font(Fonts.useFont("chiller").getName(), Font.BOLD, Main.ppb / 2), true);
						obst.drawPixel(0, 0, ColorRamp.ryg.use((double) (Main.maxobsthp - strength / Main.cSP) / (double)Main.maxobsthp));
						Main.addScore(str);
					}
				}
			}
		} catch(ConcurrentModificationException ex) {
			//ex.printStackTrace();
			System.out.println("CME");
		}
		tt++;
		if(tt > Main.ppb) { // table top will be moved up every time the row is over
			tt = 0;
			Main.pX.spawnObst(Obstacle.obstacle(iterate), ThreadLocalRandom.current().nextInt(1, 6));
			iterate++;
		}
			//System.out.println("new row");
		if(Main.pchar.collides(obsts).size() > 0 && Main.loosable) {
			HUDManager.loose();
		}
	}
	public void restart() { // clear everything
		for(PixelPanel obst : obsts) {
			obsts.remove(obst);
			Main.mainframe.remove(obst);
		}
		for(PixelPanel pin : pins) {
			pins.remove(pin);
			Main.mainframe.remove(pin);
		}
		Main.mainframe.revalidate();;
	}
}