package world;

import window.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Maze extends JFrame{
	public static int rows = 30;
	public static int columns = 30;
	public static int panelSize = 25;
	public static int map[][] = new int[columns][rows];
	public static int endLevelLoc;
	public static actor.Player p;
	
	public Maze(String str) {
		
		loadMap(str);
		this.setResizable(false);
		this.setSize((columns * panelSize), (rows * panelSize));
		this.setTitle("Maze");
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create player
		try {
			p = new actor.Player();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setVisible(true);
		this.add(p);
        
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// Color map
		for (int y = 0; y < columns; y++) {
			for (int x = 0; x < rows; x++) {
				
				BufferedImage tile = new BufferedImage(panelSize, panelSize, BufferedImage.TYPE_INT_ARGB);
				Graphics color = tile.createGraphics();

				if (map[x][y] == 0) {
					//tile.setBackground(Color.GRAY);// wall
					color.setColor(Color.GRAY);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 1) {
					//tile.setBackground(Color.WHITE);// corridor
					color.setColor(Color.WHITE);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 2) {
					//tile.setBackground(Color.CYAN);// token
					color.setColor(Color.CYAN);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 3) {
					//tile.setBackground(Color.ORANGE);// key
					color.setColor(Color.ORANGE);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 4) {
					//tile.setBackground(Color.GREEN);// door
					color.setColor(Color.GREEN);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 5) {
					//tile.setBackground(Color.PINK);// receptacle
					color.setColor(Color.PINK);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 6) {
					//tile.setBackground(Color.RED); // block1
					color.setColor(Color.RED);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 7) {
					//tile.setBackground(Color.BLUE);// block2
					color.setColor(Color.BLUE);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				if (map[x][y] == 8) {
					//tile.setBackground(Color.YELLOW);// block3
					color.setColor(Color.YELLOW);
					color.fillRect(0, 0, panelSize, panelSize);
				}
				//tile.setWall(false);
				if (x == 0) {
				}
				if (x == columns - 1) {
					endLevelLoc = y;
				}
				
				
				g2d.drawImage(tile, (x * panelSize), ((y + 1) * panelSize), panelSize, panelSize, null);
				p.paint(g);

			}
		}

	}

	public static void main(String args[]) throws IOException, InterruptedException {
		new window.Game();
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
					if (!mapChar.equals("\r\n") && !mapChar.equals("\n") && !mapChar.equals("\r")) {// If Character.Digit(mapChar)
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
