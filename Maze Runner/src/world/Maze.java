package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Maze {
	public static int rows = 30;
	public static int columns = 30;
	public static int panelSize = 25;
	public static char map[][] = new char[columns][rows];
	public static int endLevelLoc;
	boolean go;

	public Maze(String str) throws IOException {
		loadMap(str);
		go = true;

	}

	public void paint(Graphics g) throws IOException {
		Graphics2D g2d = (Graphics2D) g;
		// Color map
		do {
			for (int y = 0; y < columns; y++) { // systematically works through
												// the list to add the graphics
												// of the maze
				for (int x = 0; x < rows; x++) {
					BufferedImage tile = new BufferedImage(panelSize, panelSize, BufferedImage.TYPE_INT_ARGB);
					Graphics color = tile.createGraphics();

					if (map[x][y] == 'a') {
						// wall
						color.setColor(Color.BLACK);
						color.fillRect(0, 0, panelSize, panelSize);
					}
					if (map[x][y] == 'b') {
						// corridor
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\brickFloor.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'c') {
						// goal
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\goal.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'd') {
						// door
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\door.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'e') {
						// door
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\door90.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'f') {
						// key
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\key.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'g') {
						// receptacle
						color.setColor(Color.PINK);
						color.fillRect(0, 0, panelSize, panelSize);
					}
					if (map[x][y] == 'h') {
						// block1
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\stoneBlockRed.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'i') {
						// block2
						BufferedImage in = ImageIO.read(new File("data\\sprites\\terrain\\stoneBlockBlue.png"));
						color.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
						color.dispose();
					}
					if (map[x][y] == 'j') {
						// block3
						color.setColor(Color.YELLOW);
						color.fillRect(0, 0, panelSize, panelSize);
					}
					// tile.setWall(false);
					if (x == 0) {
					}
					if (x == columns - 1) {
						endLevelLoc = y;
					}

					g2d.drawImage(tile, (x * panelSize), (y * panelSize), panelSize, panelSize, null);

				}
			}
			go = false;
		} while (go);

	}

	@SuppressWarnings("resource")
	public void loadMap(String str) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(str));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String mapStr = sb.toString();

			int counter = 0;
			for (int y = 0; y < columns; y++) {
				for (int x = 0; x < rows; x++) {
					String mapChar = mapStr.substring(counter, counter + 1);
					if (!mapChar.equals("\r\n") && !mapChar.equals("\n") && !mapChar.equals("\r")) {// If
																									// it's
																									// a
																									// number
						// System.out.print(mapChar);
						map[x][y] = mapChar.charAt(0);
					} else {// If it is a line break
						x--;
						System.out.print(mapChar);
					}
					counter++;
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to load existing map(if exists), creating new map.");
		}
	}
}
