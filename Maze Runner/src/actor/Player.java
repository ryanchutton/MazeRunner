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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {
	public int x = 0, y = 0;
	public static int keys = 0;
	private BufferedImage knightDrawn;
	private world.Tile tile;

	public Player() throws IOException {
		BufferedImage in = ImageIO.read(new File("data\\sprites\\actor\\player.png"));
		BufferedImage knight = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = knight.createGraphics();
		g.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
		g.dispose();
		knightDrawn = knight;
	}

	public void keyKeys() {
		int deltaX = 0, deltaY = 0;
		if (window.Game.key.keyRight) {
			deltaX += 25;
		}
		if (window.Game.key.keyLeft) {
			deltaX -= 25;
		}

		if (window.Game.key.keyUp) {
			deltaY -= 25;
		}
		if (window.Game.key.keyDown) {
			deltaY += 25;
		}

		move(deltaX, deltaY);
	}

	public void move(int deltaX, int deltaY) {
		boolean go = true;

		int nextX = deltaX + this.x;
		int nextY = deltaY + this.y;

		int tileX = nextX / 25;
		int tileY = nextY / 25;

		if (tileX < 0) {
			go = false;
		}

		if (tileY < 0) {
			go = false;
		}
		if (go) {
			if (world.Maze.map[tileX][tileY] == 1) {
				this.x = nextX;
				this.y = nextY;
			}
			if (world.Maze.map[tileX][tileY] == 2) {
				this.x = nextX;
				this.y = nextY;
				JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game",
						JOptionPane.INFORMATION_MESSAGE);
				window.Game.frame.dispose();
				new window.Game();

			}
			if (world.Maze.map[tileX][tileY] == 3) {
				world.Maze.map[tileX][tileY] = 1;
				tile = new world.Tile(Color.WHITE, nextX, nextY);
				actor.Player.addKey(1);
			}
			if (world.Maze.map[tileX][tileY] == 4) {
				if (actor.Player.keys > 0) {
					world.Maze.map[tileX][tileY] = 1;
					tile = new world.Tile(Color.WHITE, nextX, nextY);
					actor.Player.removeKey(1);
				}
			}
		}
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
		if (tile != null) {
			tile.paint(g2d);
		}
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
