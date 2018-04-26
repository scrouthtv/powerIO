package me.scrtv.main;

import me.scrtv.utils.PixelPanel;

public class ParticleEmitter implements Runnable {
	private int x, y, lifetime, alive;
	private PixelPanel particles;
	private double amount;
	// amount in 0.0...1.0
	// lifetime in ticks
	public ParticleEmitter(int x, int y, PixelPanel particles, double amount, int lifetime) {
		this.x = x;
		this.y = y;
		this.particles = particles;
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
