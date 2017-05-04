package actor;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {
	public int x, y;

	public Player() {
		this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
		this.setSize(world.Maze.panelSize, world.Maze.panelSize);
	}

	/**
	 * public void moveLeft() { if (x > 0 && world.Maze.map[x - 1][y] == 1) {
	 * this.setLocation(this.getX() - 25, this.getY()); x--; } }
	 * 
	 * public void moveRight() { if (x < world.Maze.columns - 1 &&
	 * world.Maze.map[x + 1][y] == 1) { this.setLocation(this.getX() + 25,
	 * this.getY()); x++; } }
	 * 
	 * public void moveUp() { if (y > 0 && world.Maze.map[x][y - 1] == 1) {
	 * this.setLocation(this.getX(), this.getY() - 25); y--; } }
	 * 
	 * public void moveDown() { if (y < world.Maze.rows - 1 &&
	 * world.Maze.map[x][y + 1] == 1) { this.setLocation(this.getX(),
	 * this.getY() + 25); y++; } }
	 */
}
