package window;

import java.awt.FlowLayout;
import java.awt.GridLayout;
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


public class help extends JFrame {
	
	
	public static JFrame help = new JFrame("Help Menu");
	JLabel text = new JLabel("<html>Welcome to MAZE RUNNER <br>HOW TO PLAY: <br> 1) Collect Yellow Blocks to unlock green doors <br> 2) Push red and blue blocks <br> 3) Get to the cyan blocks to finish the level!</html>");
	JButton menu = new JButton("Main Menu");
	JButton exit = new JButton("Exit");
	int menuWidth = 100; //Width of each button/item on display
	int menuHeight = 30;//Height of each button/item on display
	int menuY = 460; //Button/item location on display
	int WIDTH = 290;
	int HEIGHT = 200;


	
	 
	public help(){
	    
		
	  //frame variables
	  		help = new JFrame("Help Menu");
	  		help.setSize(WIDTH, HEIGHT);
	  		help.setVisible(true);
	      	help.setResizable(false);        
	        help.setLayout(new FlowLayout());
	        help.setLocationRelativeTo(null);        
	  		help.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  		help.add(text);
	 
	  	
	  		
	  	//Start Button Variables
	        menu.setSize(menuWidth,menuHeight);
	        menu.setLocation(10, menuY);
	        help.add(menu);
	        menu.addActionListener(new ActionListener(){
	 
				@Override
				public void actionPerformed(ActionEvent arg0) {
					help.setVisible(false);
					
				    try {
						window.Game.create();						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
	        	
	        });	
	        
	      //Exit Button Variables
	        exit.setSize(menuWidth,menuHeight);
	        exit.setLocation(475,menuY);
	        help.add(exit);
	        exit.addActionListener(new ActionListener(){
	 
				@Override
				public void actionPerformed(ActionEvent e) {
		            System.exit(0);
				}
	        }); 
	        
	   	}
	      
	}



