package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	public static Game game = new Game();
	public static int panelSize = 25, frameWidth = 500, frameHeight = 500;
	public static actor.Player p;
	public static actor.KeyManager key;
	public static world.Maze m;

	public void paint(Graphics g) {
		super.paint(g);
		p.paint(g);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		p = new actor.Player();
		key = new actor.KeyManager();
		m = new world.Maze(str, plrX, plrY)
		JFrame frame = new JFrame("Maze Runner");
		frame.add(game);
		frame.add
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addKeyListener(key);

		while (true) {
			game.repaint();
			key.update();
			Thread.sleep(10);
		}
	}
}
