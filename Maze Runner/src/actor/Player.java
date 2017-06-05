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
		// calculates the x and y movement of the player
		// supports arrow keys and wasd
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

	public void move(int deltaX, int deltaY) { // move method to move the player
		boolean go = true;
		// calculates
		int nextX = deltaX + this.x; // new X position
		int nextY = deltaY + this.y;// next Y position

		int tileX = nextX / 25;
		int tileY = nextY / 25;

		if (tileX < 0) {
			go = false;
		}

		if (tileY < 0) {
			go = false;
		}
		if (go) {
			if (world.Maze.map[tileX][tileY] == 'b') { // tile 1 is the wall
				this.x = nextX;
				this.y = nextY;
			}
			if (world.Maze.map[tileX][tileY] == 'c') { // the end block
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

			}
			if (world.Maze.map[tileX][tileY] == 'f') { // adds key
				world.Maze.map[tileX][tileY] = 'b';
				try {
					tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				actor.Player.addKey(1);
				this.x = nextX;
				this.y = nextY;
			}
			if (world.Maze.map[tileX][tileY] == 'd') { // door, removes key
				if (actor.Player.keys > 0) {
					world.Maze.map[tileX][tileY] = 'b';
					try {
						tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					actor.Player.removeKey(1);
				}
				this.x = nextX;
				this.y = nextY;
			}
			if (world.Maze.map[tileX][tileY] == 'e') { // door, removes key
				if (actor.Player.keys > 0) {
					world.Maze.map[tileX][tileY] = 'b';
					try {
						tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					actor.Player.removeKey(1);
				}
				this.x = nextX;
				this.y = nextY;
			}
			if (world.Maze.map[tileX][tileY] == 'h') { // red block
				if (deltaX == 0) { // calculates where the block will go and
									// checks if the destination is valid
					try {
						tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (deltaY < 0) {
						if (world.Maze.map[tileX][tileY - 1] == 'b') {
							world.Maze.map[tileX][tileY - 1] = 'h';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockRed.png", nextX,
										nextY - 25);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}
					if (deltaY > 0) {
						if (world.Maze.map[tileX][tileY + 1] == 'b') {
							world.Maze.map[tileX][tileY + 1] = 'h';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockRed.png", nextX,
										nextY + 25);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}

				}

				if (deltaY == 0) {// calculates where the block will go and
									// checks if the destination is valid
					try {
						tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (deltaX < 0) {
						if (world.Maze.map[tileX - 1][tileY] == 'b') {
							world.Maze.map[tileX - 1][tileY] = 'h';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockRed.png", nextX - 25,
										nextY);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}
					if (deltaX > 0) {
						if (world.Maze.map[tileX + 1][tileY] == 'b') {
							world.Maze.map[tileX + 1][tileY] = 'h';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockRed.png", nextX + 25,
										nextY);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}
				}
			}
			if (world.Maze.map[tileX][tileY] == 'i') {
				if (deltaX == 0) {// calculates where the block will go and
									// checks if the destination is valid
					try {
						tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (deltaY < 0) {
						if (world.Maze.map[tileX][tileY - 1] == 'b') {
							world.Maze.map[tileX][tileY - 1] = 'i';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockBlue.png", nextX,
										nextY - 25);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}
					if (deltaY > 0) {
						if (world.Maze.map[tileX][tileY + 1] == 'b') {
							world.Maze.map[tileX][tileY + 1] = 'i';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockBlue.png", nextX,
										nextY + 25);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}

				}

				if (deltaY == 0) {// calculates where the block will go and
									// checks if the destination is valid
					try {
						tile = new world.Tile("data\\sprites\\terrain\\brickFloor.png", nextX, nextY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (deltaX < 0) {
						if (world.Maze.map[tileX - 1][tileY] == 'b') {
							world.Maze.map[tileX - 1][tileY] = 'i';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockBlue.png", nextX - 25,
										nextY);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
						}
					}
					if (deltaX > 0) {
						if (world.Maze.map[tileX + 1][tileY] == 'b') {
							world.Maze.map[tileX + 1][tileY] = 'i';
							try {
								tileBlock = new world.Tile("data\\sprites\\terrain\\stoneBlockBlue.png", nextX + 25,
										nextY);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							world.Maze.map[tileX][tileY] = 'b';
							this.x = nextX;
							this.y = nextY;
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
