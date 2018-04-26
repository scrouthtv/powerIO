package me.scrtv.utils;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map.Entry;

public enum ColorRamp {
	ryg(new double[] {.0, .5, 1.0}, new Color[] {Color.RED, Color.YELLOW, Color.GREEN}),
	tst(new double[] {.0, 1.0}, new Color[] {new Color(100, 100, 100), new Color(100, 100, 100)});

	private HashMap<Double, Color> steps = new HashMap<Double, Color>();
	ColorRamp(double[] indxs, Color[] vals) {
		for(int i = 0; i < Math.min(indxs.length, vals.length); i++) {
			steps.put(indxs[i], vals[i]);
		}
	}
	public Color use(double x) {
		if(steps.keySet().contains(x))
			return steps.get(x);

		// calculate borders
		double sr = 0;
		double lr = 0;
		for(Entry<Double, Color> step : steps.entrySet()) {
			if(step.getKey() < x)
				sr = step.getKey();
			else {
				lr = step.getKey();
				break;
			}
		}
		double sx = x - sr;
		double lx = lr - x;
		int r = (int) ((steps.get(sr).getRed() * lx + steps.get(lr).getRed() * sx) / (sx + lx));
		int g = (int) ((steps.get(sr).getGreen() * lx + steps.get(lr).getGreen() * sx) / (sx + lx));
		int b = (int) ((steps.get(sr).getBlue() * lx + steps.get(lr).getBlue() * sx) / (sx + lx));
		return new Color(r, g, b);
	}
}