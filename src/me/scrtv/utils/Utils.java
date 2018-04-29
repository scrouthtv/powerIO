package me.scrtv.utils;

import java.awt.Color;
import java.util.HashMap;

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
	public static HashMap<Double, Color> pa2hm(Double[] indxs, Color[] vals) {
		HashMap<Double, Color> hm = new HashMap<Double, Color>();
		for(int i = 0; i < indxs.length; i++) {
			hm.put(indxs[i], vals[i]);
		}
		return hm;
	}
	public static boolean prcontains(double[] arr, double x) {
		for(double d : arr)
			if(d == x)
				return true;
		return false;
	}
	public static String removeExt(String f) {
		String[] parts = f.split("\\.");
		String fn = "";
		for(int i = 0; i < (parts.length - 1); i++) {
			fn += parts[i];
		}
		return fn;
	}
}