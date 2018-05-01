package me.scrtv.utils;

public enum Side {
	N(0), E(90), S(180), W(270);
	
	private int r;
	
	private Side(int r) {
		this.r = r;
	}

	public int getR() {
		return r;
	}
}