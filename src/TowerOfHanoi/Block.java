package TowerOfHanoi;

import java.awt.Color;

public class Block {

	// Height of block. Blocks have the same HEIGHT
	final int HEIGHT = 25;

	// Each block has its own color and width
	int width;
	Color color;

	public Block() {
		this(0, null);
	}

	public Block(int w, Color c) {
		width = w;
		color = c;
	}
	
	// returns block height
	public int getHeight() {
		return HEIGHT;
	}

	// returns block width
	public int getWidth() {
		return width;
	}

	// returns block color
	public Color getColor() {
		return color;
	}
}

