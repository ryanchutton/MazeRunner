package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JFrame {

	public static Game game;		   
	public static actor.Player p;
	public static actor.KeyManager key;
	public static world.Maze m;
	private static String str;
	public static JFrame frame;
    JButton Start = new JButton("Play");
    JButton Exit = new JButton("Exit");
    ImageIcon picture = new ImageIcon("res/Images/MazePicture.png");
    JLabel imageLabel = new JLabel(picture);
    ArrayList<String> mapList = new ArrayList<String>();
    JComboBox<String> lvlList;
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 460; //Button/item location on display
    int WIDTH = 590;
    int HEIGHT = 530;
    
    public static void main(String[] args)throws IOException, InterruptedException{
    	Game g = new Game();
    }
	
	public Game() throws IOException, InterruptedException  {
		
		//Load map list
    	getMapList();
    	lvlList = new JComboBox<String>(mapList.toArray(new String[mapList.size()]));
		
    	
		game = new Game();
		p = new actor.Player();
		key = new actor.KeyManager();
		
		
		//frame variables
		frame = new JFrame("Maze Runner");
		frame.setSize(WIDTH, HEIGHT);
		frame.add(game);
		frame.setVisible(true);
    	frame.setResizable(false);        
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Level Selector
        lvlList.setSize(menuWidth+35, menuHeight);
        lvlList.setLocation(230, menuY);
        frame.add(lvlList);
        
        //Display Picture
        imageLabel.setBounds((WIDTH-412)/2, 25, 412, 412);
        imageLabel.setVisible(true);
        frame.add(imageLabel);
        frame.setVisible(true);
        
		//Exit Button Variables
        Exit.setSize(menuWidth,menuHeight);
        Exit.setLocation(375,menuY);
        frame.add(Exit);
        Exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	            System.exit(0);
			}
        });
		
		//Start Button Variables
        Start.setSize(menuWidth,menuHeight);
        Start.setLocation(10, menuY);
        frame.add(Start);
        Start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					m = new world.Maze(lvlList.getSelectedItem().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(false);
			}
        	
        });	
       /** 
		while (true) {
			game.repaint();
			key.update();
			Thread.sleep(125);
		}
		*/
	}
	
	
	static boolean levelsExistAlready = false;
	
	public void getMapList(){
    	for(int i = 0; i < 99; i++){
    		File map = new File("data\\maps\\" + "./Level "+i+".map");
    		if(map.exists()){
    			System.out.println("Level "+i+" exists");
    			mapList.add("data\\maps\\" + "Level "+i+".map");
    			levelsExistAlready = true;
    		} 
    	}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		m.paint(g);
		p.paint(g);
	}
	
}