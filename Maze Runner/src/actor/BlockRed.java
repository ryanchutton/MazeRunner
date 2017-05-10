package actor;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BlockRed extends Block {
	public int x, y;
	
	public BlockRed() {
		super();
		this.setBackground(Color.RED);
	}
}
