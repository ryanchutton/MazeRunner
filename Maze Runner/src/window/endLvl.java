package window;


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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class endLvl extends JFrame {
	
	public static JFrame end = new JFrame("Level Complete");
    JButton nextLvl = new JButton("Next Level");
    JButton exit = new JButton("Exit");
    public static world.Maze m;
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 460; //Button/item location on display
    int WIDTH = 590;
    int HEIGHT = 530;
    ArrayList<String> mapList = new ArrayList<String>();
    public endLvl(){
    
  //game variables
  		end = new JFrame("Level Complete");
  		end.setSize(WIDTH, HEIGHT);
  		end.setVisible(true);
      	end.setResizable(false);        
        end.setLayout(null);
        end.setLocationRelativeTo(null);        
  		end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  	//Exit Button Variables
        exit.setSize(menuWidth,menuHeight);
        exit.setLocation(375,menuY);
        end.add(exit);
        exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	            System.exit(0);
			}
        });
  		
  	//Start Button Variables
        nextLvl.setSize(menuWidth,menuHeight);
        nextLvl.setLocation(10, menuY);
        end.add(nextLvl);
        nextLvl.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO find way to get current lvl and iterate
				m = new world.Maze(mapList.get(0));
				end.setVisible(false);
			}
        	
        });	
  			
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
}
