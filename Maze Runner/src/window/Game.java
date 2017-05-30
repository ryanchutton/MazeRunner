package window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Game extends JFrame {
 
		   
	public static actor.Player p;
	public static actor.KeyManager key;
	public static world.Maze m;
	//public static JFrame frame;
	public static JFrame select = new JFrame("Maze");
    static JButton Start = new JButton("Play");
    static JButton Exit = new JButton("Exit");
    static ImageIcon picture = new ImageIcon("res/Images/MazePicture.png");
    static JLabel imageLabel = new JLabel(picture);
    static ArrayList<String> mapList = new ArrayList<String>();
    static JComboBox<String> lvlList;
    static int menuWidth = 100; //Width of each button/item on display
    static int menuHeight = 30;//Height of each button/item on display
    static int menuY = 460; //Button/item location on display
    static int WIDTH = 590;
    static int HEIGHT = 530;
    
    
	public static void main(String[] args) throws IOException, InterruptedException  {
		
		//Load map list
    	getMapList();
    	lvlList = new JComboBox<String>(mapList.toArray(new String[mapList.size()]));
		
    	
		//game = new Game();
		p = new actor.Player();
		key = new actor.KeyManager();
		
		
		//game variables
		select = new JFrame("Maze Runner");
		select.setSize(WIDTH, HEIGHT);
		select.setVisible(true);
    	select.setResizable(false);        
        select.setLayout(null);
        select.setLocationRelativeTo(null);        
		select.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		//Level Selector
        lvlList.setSize(menuWidth+35, menuHeight);
        lvlList.setLocation(230, menuY);
        select.add(lvlList);
        
        //Display Picture
        imageLabel.setBounds((WIDTH-412)/2, 25, 412, 412);
        imageLabel.setVisible(true);
        select.add(imageLabel);
        select.setVisible(true);
        
		//Exit Button Variables
        Exit.setSize(menuWidth,menuHeight);
        Exit.setLocation(375,menuY);
        select.add(Exit);
        Exit.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent e) {
	            System.exit(0);
			}
        });
		
		//Start Button Variables
        Start.setSize(menuWidth,menuHeight);
        Start.setLocation(10, menuY);
        select.add(Start);
        Start.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadMap(lvlList.getSelectedItem().toString());
			}
        	
        });
        
        
       
        while (true) {
			select.repaint();
			key.update();
			Thread.sleep(125);
		}
		
	}
	
	
	static boolean levelsExistAlready = false;
	
	public static void getMapList(){
    	for(int i = 0; i < 99; i++){
    		File map = new File("data\\maps\\" + "./Level "+i+".map");
    		if(map.exists()){
    			System.out.println("Level "+i+" exists");
    			mapList.add("data\\maps\\" + "Level "+i+".map");
    			levelsExistAlready = true;
    		}    		
    	}
	}
	
	public static void loadMap(String str) {

		try {
			m = new world.Maze(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		m.paint(g);
		p.paint(g);
	}
	
}
