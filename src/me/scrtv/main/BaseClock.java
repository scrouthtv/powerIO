package me.scrtv.main;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class BaseClock extends TimerTask {
	private long tick = 0;
	private HashMap<Runnable, Integer> listeners = new HashMap<Runnable, Integer>(); 
	private final static long period = 20; // tick time in ms
	public BaseClock() {
		Timer t = new Timer();
		TimerTask tt = this;
		t.scheduleAtFixedRate(tt, 0, period);
	}
	@Override
	public void run() {
		Set<Entry<Runnable, Integer>> rs = listeners.entrySet();
		for(Entry<Runnable, Integer> r : rs) {
			if(tick % r.getValue() == 0)
				r.getKey().run();
			}
		tick++;
	}
	//frequency: 1 => every tick e. g. every 20 ms
	public void addListener(Runnable r, int frequency) { 
		listeners.put(r, frequency);
	}
	public void removeListener(Runnable r) {
		listeners.remove(r);
	}
}