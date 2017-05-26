package world;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tile extends JPanel {
	int x, y;
	Color color;

	public Tile(Color color, int x, int y) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, world.Maze.panelSize, world.Maze.panelSize);
	}
}