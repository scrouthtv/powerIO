package me.scrtv.main;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class BaseClock extends TimerTask {
	private long tick = 0;
	private Timer t;
	private HashMap<Runnable, Integer> listeners = new HashMap<Runnable, Integer>(); 
	private final static long period = 20; // tick time in ms
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
	public void clear() {
		t.cancel();
		t.purge();
	}
	public static void start() {
		Timer t = new Timer();
		Main.clock = new BaseClock();
		t.scheduleAtFixedRate(Main.clock, 0, period);
		Main.clock.addListener(Main.pX, 1);
		Main.clock.addListener(new Tests(), 25); // shoot every 25 ticks
		Main.clock.addListener(new Runnable() {
			@Override
			public void run() {
				Main.mainframe.revalidate();
				Main.mainframe.repaint();
			}}, 1);
	}
}