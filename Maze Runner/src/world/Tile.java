package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tile extends JPanel { //tile for drawing the map
	int x, y;
	String path;
	BufferedImage tile;

	public Tile(String path, int x, int y) throws IOException {
		this.x = x;
		this.y = y;
		this.path = path;
		tile = new BufferedImage(world.Maze.panelSize, world.Maze.panelSize, BufferedImage.TYPE_INT_ARGB);
		Graphics color = tile.createGraphics();
		BufferedImage in = ImageIO.read(new File(path));
		color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
		color.dispose();
	}
	public void paint(Graphics g) {
		g.drawImage(tile, x, y, world.Maze.panelSize, world.Maze.panelSize, null);
	}
}
