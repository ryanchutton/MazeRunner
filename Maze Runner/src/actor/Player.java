package actor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	public int x, y;
	public static int keys = 0;
	
	public Player() throws IOException {
		this.setLayout(null);
		BufferedImage in = ImageIO.read(new File("data\\sprites\\actor\\player.png"));
		BufferedImage knight = new BufferedImage( in.getWidth(), in.getHeight(), BufferedImage.	TYPE_INT_ARGB);
		Graphics2D g = knight.createGraphics();
		g.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
		g.dispose();
		
		JLabel picLabel = new JLabel(new ImageIcon(knight));
		picLabel.setPreferredSize(new Dimension(25, 25));
		picLabel.setBounds(0, 0, 25, 25);
		add(picLabel);
		this.setSize(world.Maze.panelSize, world.Maze.panelSize);
	}

	public static void addKey(int i) {
		keys += i;
	}
	
	public static void removeKey(int i) {
		keys -= i;
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
