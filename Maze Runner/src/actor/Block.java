package actor;

import java.awt.Color;

import javax.swing.JPanel;

public class Block extends JPanel {

public int x, y;
	
	public Block() {
		this.setSize(world.Maze.panelSize, world.Maze.panelSize);
	}
}
