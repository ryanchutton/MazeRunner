package window;

import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	
	public static Game game;
	public static int frameWidth = 500, frameHeight = 500;
	public static actor.Player p;
	public static actor.KeyManager key;
	public static world.Maze m;
	private static String str;
	public static JFrame frame;

	public void paint(Graphics g) {
		super.paint(g);
		m.paint(g);
		p.paint(g);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		create();
		
		while (true) {
			game.repaint();
			key.update();
			Thread.sleep(125);
		}
	}

	public static void loadMap() throws IOException {
		str = JOptionPane.showInputDialog(null, "Please enter level number. \n Type 'quit' to exit and type '?' for help");
		if (str.length() < 1) {
			loadMap();
		}
		else if(str.toLowerCase().equals("quit")){		
			System.exit(0);
		}
		else if(str.toLowerCase().equals("?")){
			window.help h = new window.help();
			frame.setVisible(false);
		}
		m = new world.Maze("data\\maps\\" + "Level " + str + ".map"); // "data\\maps\\"
																		// +
																		// "Level
																		// "+"1"+".map"
	}

	public static void create() throws IOException {

		game = new Game();
		p = new actor.Player();
		key = new actor.KeyManager();
		loadMap();
		frame = new JFrame("Maze Runner");
		frame.setSize(750, 750);
		frame.add(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addKeyListener(key);
	}

}
