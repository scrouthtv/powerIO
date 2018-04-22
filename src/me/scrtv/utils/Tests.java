package me.scrtv.utils;

import java.awt.Color;

import me.scrtv.main.Main;

public class Tests implements Runnable {

	public static void main(String[] args) {
		int x = 1;
		int scale = 4;
		System.out.println(x * scale);
		System.out.println((x + 1) * scale - 1);
		
		System.out.println(Color.OPAQUE);
		System.out.println("wenn du das hier liest hast du die falsche klasse gestartet");
		System.out.println(Utils.parseInt(15345));
	}

	@Override
	public void run() {
		Main.shoot();
	}
}