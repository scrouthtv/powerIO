package me.scrtv.main;

import java.util.ArrayList;
import java.util.List;

import me.scrtv.utils.PixelPanel;

public class ParticleEmitter implements Runnable {
	// TODO particle emitter + rotation of pixelpanel (?!)
	private int x, y, lifetime, alive;
	private PixelPanel particle;
	private double amount;
	private List<PixelPanel> particles = new ArrayList<PixelPanel>();
	// amount in 0.0...1.0
	// lifetime in ticks
	public ParticleEmitter(int x, int y, PixelPanel particle, double amount, int lifetime) {
		this.x = x;
		this.y = y;
		this.particle = particle;
		this.amount = amount;
		Main.clock.addListener(this, 1);
		this.lifetime = lifetime;
		alive = 0;
	}
	@Override
	public void run() {
		if(alive >= lifetime)
			Main.clock.removeListener(this);
		else {
			
		}
		alive++;
	}
}
