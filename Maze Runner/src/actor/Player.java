package actor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {
	public int x = 0, y = 0;
	public static int keys = 0;
	private BufferedImage knightDrawn;
	private world.Tile tile;
	private world.Tile tileBlock;

	public Player() throws IOException {
		// creates the player sprite
		BufferedImage in = ImageIO.read(new File("data\\sprites\\actor\\player.png"));
		BufferedImage knight = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = knight.createGraphics();
		g.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
		g.dispose();
		knightDrawn = knight;
	}

	public void keyKeys() {
		int deltaX = 0, deltaY = 0;
		if (window.Game.key.keyRight || window.Game.key.arrowRight) {
			deltaX += 25;
		}
		if (window.Game.key.keyLeft || window.Game.key.arrowLeft) {
			deltaX -= 25;
		}

		if (window.Game.key.keyUp || window.Game.key.arrowUp) {
			deltaY -= 25;
		}
		if (window.Game.key.keyDown || window.Game.key.arrowDown) {
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
				try {
					window.Game.create();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.x = 0;
				this.y = 0;

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
			if (world.Maze.map[tileX][tileY] == 6) {
				if (deltaX == 0) {
					tile = new world.Tile(Color.WHITE, nextX, nextY);
					if (deltaY < 0) {
						if (world.Maze.map[tileX][tileY - 1] == 1) {
							world.Maze.map[tileX][tileY - 1] = 6;
							tileBlock = new world.Tile(Color.RED, nextX, nextY - 25);
							world.Maze.map[tileX][tileY] = 1;
						}
					}
					if (deltaY > 0) {
						if (world.Maze.map[tileX][tileY + 1] == 1) {
							world.Maze.map[tileX][tileY + 1] = 6;
							tileBlock = new world.Tile(Color.RED, nextX, nextY + 25);
							world.Maze.map[tileX][tileY] = 1;
						}
					}

				}

				if (deltaY == 0) {
					tile = new world.Tile(Color.WHITE, nextX, nextY);
					if (deltaX < 0) {
						if (world.Maze.map[tileX - 1][tileY] == 1) {
							world.Maze.map[tileX - 1][tileY] = 6;
							tileBlock = new world.Tile(Color.RED, nextX - 25, nextY);
							world.Maze.map[tileX][tileY] = 1;
						}
					}
					if (deltaX > 0) {
						if (world.Maze.map[tileX + 1][tileY] == 1) {
							world.Maze.map[tileX + 1][tileY] = 6;
							tileBlock = new world.Tile(Color.RED, nextX + 25, nextY);
							world.Maze.map[tileX][tileY] = 1;
						}
					}
				}
			}
			if (world.Maze.map[tileX][tileY] == 7) {
				if (deltaX == 0) {
					tile = new world.Tile(Color.WHITE, nextX, nextY);
					if (deltaY < 0) {
						if (world.Maze.map[tileX][tileY - 1] == 1) {
							world.Maze.map[tileX][tileY - 1] = 6;
							tileBlock = new world.Tile(Color.BLUE, nextX, nextY - 25);
							world.Maze.map[tileX][tileY] = 1;
						}
					}
					if (deltaY > 0) {
						if (world.Maze.map[tileX][tileY + 1] == 1) {
							world.Maze.map[tileX][tileY + 1] = 7;
							tileBlock = new world.Tile(Color.BLUE, nextX, nextY + 25);
							world.Maze.map[tileX][tileY] = 1;
						}
					}
 
				}
 
				if (deltaY == 0) {
					tile = new world.Tile(Color.WHITE, nextX, nextY);
					if (deltaX < 0) {
						if (world.Maze.map[tileX - 1][tileY] == 1) {
							world.Maze.map[tileX - 1][tileY] = 7;
							tileBlock = new world.Tile(Color.BLUE, nextX - 25, nextY);
							world.Maze.map[tileX][tileY] = 1;
						}
					}
					if (deltaX > 0) {
						if (world.Maze.map[tileX + 1][tileY] == 1) {
							world.Maze.map[tileX + 1][tileY] = 7;
							tileBlock = new world.Tile(Color.BLUE, nextX + 25, nextY);
							world.Maze.map[tileX][tileY] = 1;
						}
 
					}
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
		if (tileBlock != null) {
			tileBlock.paint(g2d);
		}
		g2d.drawImage(knightDrawn, x, y, 25, 25, null);

	}
}
