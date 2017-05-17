package actor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {
	public int x = 0, y = 0, deltaX = 0, deltaY = 0;
	public static int keys = 0;
	private BufferedImage knightDrawn;

	public Player() throws IOException {
		BufferedImage in = ImageIO.read(new File("data\\sprites\\actor\\player.png"));
		BufferedImage knight = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = knight.createGraphics();
		g.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
		g.dispose();
		knightDrawn = knight;
	}

	public void keyKeys() {
		if (window.Game.key.keyRight) {
			deltaX += 2;
		}
		if (window.Game.key.keyLeft) {
			deltaX -= 2;
		}
		
		if (window.Game.key.keyUp) {
			deltaY -= 2;
		}
		if (window.Game.key.keyDown) {
			deltaY += 2;
		}
		
		move(deltaX, deltaY);
		
		deltaX = 0;
		deltaY = 0;
	}

	public void move(int x, int y) {
		this.x = x + this.x;
		this.y = y + this.y;
	}

	public static void addKey(int i) {
		keys += i;
	}

	public static void removeKey(int i) {
		keys -= i;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		keyKeys();
		g2d.drawImage(knightDrawn, x, y, 25, 25, null);
		
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
