package me.scrtv.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import me.scrtv.utils.PixelPanel;

public class Movement implements KeyListener {
	PixelPanel panel;
	
	Movement(PixelPanel pan) {
		panel = pan;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			Main.pchar.move(10, 0);
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			Main.pchar.move(-10, 0);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}