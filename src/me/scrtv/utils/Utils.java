package me.scrtv.utils;

import java.awt.Color;

public class Utils {
	public static boolean contains(Side[] sides, Side side) {
		for(Side s : sides)
			if(s == side)
				return true;
		return false;
	}
	public static String parseInt(int number) {
		// Million - Billion - Trillion - Quadrillion
		System.out.println(number);
		if(number > (Math.pow(10.0, 15.0)))
			return Double.toString(Math.round((double) number / Math.pow(10.0, 14.0)) / 10.0) + "Q";
		else if(number >= (Math.pow(10.0, 12.0)))
			return Double.toString(Math.round((double) number / Math.pow(10.0, 11.0)) / 10.0) + "T";
		else if(number >= (Math.pow(10.0, 9.0)))
			return Double.toString(Math.round((double) number / Math.pow(10.0, 8.0)) / 10.0) + "B";
		else if(number >= (Math.pow(10.0, 6.0)))
			return Double.toString(Math.round((double) number / Math.pow(10.0, 5.0)) / 10.0) + "M";
		else if(number >= (Math.pow(10.0, 3.0)))
			return Double.toString(Math.round((double) number / Math.pow(10.0, 2.0)) / 10.0) + "K"; 
		else
			return Integer.toString(number);
	}
	public static Color cramp(int pos) { // color ramp
		float x = (float) pos;
		float r = 76.0f + 16.8f * x;
		r = Math.min(Math.max(0, r), 255);
		float g = 175.0f - 10.8f * x;
		g = Math.min(Math.max(0, g), 255);
		float b = 80.0f - 2.6f * x;
		b = Math.min(Math.max(0, b), 255);
		return new Color(r / 255, g / 255, b / 255);
	}
}