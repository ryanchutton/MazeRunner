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
        try {
			m.paint(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        str = JOptionPane.showInputDialog(null, "WELCOME TO MAZE RUNNER \n"
                + "How To Play: \n  1) Collect the yellow blocks to unlock the "
                + "green doors \n  2) Push red and blue blocks \n  3) Get to "
                + "the cyan blocks to finish the level! \n\n Please enter level number 1-7. Type \"quit\" to quit.\n");
        if (str == null) {
            System.exit(0);
        }
        if (str.length() < 1) {
            loadMap();
        }
        else if(str.toLowerCase().equals("quit")){      
            System.exit(0);
        }
        m = new world.Maze("data\\maps\\" + "Level " + str + ".map");// "data\\maps\\"
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
