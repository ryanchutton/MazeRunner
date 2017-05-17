package world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class Maze extends JFrame {
	public static int rows = 30;
	public static int columns = 30;
	public static int panelSize = 25;
	public static int map[][] = new int[columns][rows];
	public static int endLevelLoc;
	public static actor.Player p;

	public Maze(String str, int plrX, int plrY) throws IOException {
		loadMap(str);
		this.setResizable(false);
		this.setSize((columns * panelSize) + 50, (rows * panelSize) + 70);
		this.setTitle("Maze");
		this.setLayout(null);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
				System.exit(0);
			}
		});

		this.setLocationRelativeTo(null);

		// Create player
		p = new actor.Player();
		p.setVisible(true);
		this.add(p);

		// Color map
		for (int y = 0; y < columns; y++) {
			for (int x = 0; x < rows; x++) {
				Tile tile = new Tile(x, y);
				tile.setSize(panelSize, panelSize);
				tile.setLocation((x * panelSize), (y * panelSize));

				if (map[x][y] == 0) {
					tile.setBackground(Color.GRAY);// wall
				}
				if (map[x][y] == 1) {
					tile.setBackground(Color.WHITE);// corridor
				}
				if (map[x][y] == 2) {
					tile.setBackground(Color.CYAN);// token
				}
				if (map[x][y] == 3) {
					tile.setBackground(Color.ORANGE);// key
				}
				if (map[x][y] == 4) {
					tile.setBackground(Color.GREEN);// door
				}
				if (map[x][y] == 5) {
					tile.setBackground(Color.PINK);// receptacle
				}
				if (map[x][y] == 6) {
					tile.setBackground(Color.RED); // block1
				}
				if (map[x][y] == 7) {
					tile.setBackground(Color.BLUE);// block2
				}
				if (map[x][y] == 8) {
					tile.setBackground(Color.YELLOW);// block3
				}
				tile.setWall(false);
				if (x == 0) {
					p.setLocation(plrX, plrY);
					p.y = y;
				}
				if (x == columns - 1) {
					endLevelLoc = y;
				}

			tile.setVisible(true);
			this.add(tile);
		}
	}this.setVisible(true);

	}

	public void addTile(Tile tile) {

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
						map[x][y] = Integer.parseInt(mapChar);
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
