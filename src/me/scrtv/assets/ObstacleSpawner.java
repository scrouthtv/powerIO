package me.scrtv.assets;

import java.util.concurrent.ThreadLocalRandom;

public class ObstacleSpawner {
	private static final int max = 1;
	private int type;
	private int[][] blocks;
	
	public ObstacleSpawner() {
		type = ThreadLocalRandom.current().nextInt(0, max + 1);
		
		switch(type) {
			case 0:
				blocks = new int[][] {
					{},
					{}
				};
				break;
		}
	}
	public void spawn() {
		for(int x = 0; x < blocks.length; x++) {
			for(int y = 0; y < blocks[x].length; y++) {
				
			}
		}
	}
}