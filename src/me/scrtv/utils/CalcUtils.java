package me.scrtv.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import me.scrtv.main.Main;

public class CalcUtils {
	private final static int pxoh = 7; // pixels on height
	public static Dimension calcMainFrameDimensions() {
		int width, height;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		height = screen.height * 3 / 4;
		height = (int) (Math.ceil(height / (double) pxoh) * pxoh);
		width = height / pxoh * 4;
		Main.ppb = width / 5;
		return new Dimension(width, height);
	}
	// will calculate the point to position the main frame in the middle of the window according to its size
	public static Point calcMainFramePosition() {
		Dimension dim = calcMainFrameDimensions();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int borderleft, bordertop;
		borderleft = (screen.width - dim.width) / 3;
		bordertop = (screen.height - dim.height) / 2;
		return new Point(borderleft, bordertop);
	}
}