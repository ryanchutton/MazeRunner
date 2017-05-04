package actor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MotionWithKeyBindings {

	protected JComponent component;

	public MotionWithKeyBindings(JComponent component) {
		this.component = component;
	}

	public MotionWithKeyBindings() {
	}
	
	public void move(int deltaX, int deltaY) {

		int componentWidth = component.getSize().width;
		int componentHeight = component.getSize().height;

		Dimension parentSize = component.getParent().getSize();
		int parentWidth = parentSize.width;
		int parentHeight = parentSize.height;

		// Determine next X position

		int nextX = Math.max(component.getLocation().x + deltaX, 0);

		if (nextX + componentWidth > parentWidth - 23) {
			nextX = parentWidth - 21 - componentWidth;
		}

		if (nextX == 0) {
			nextX = (int) component.getLocation().x;
		}

		// Determine next Y position

		int nextY = Math.max(component.getLocation().y + deltaY, 0);

		if (nextY + componentHeight > parentHeight - 16) {
			nextY = parentHeight - 16 - componentHeight;
		}

		if (nextY == 0) {
			nextY = (int) component.getLocation().y;
		}

		if (world.Maze.map[(nextX - 23) / world.Maze.panelSize][(nextY - 25) / world.Maze.panelSize] == 1)

		{
			// Move the component
			component.setLocation(nextX, nextY);
		}
	}

	public void attack() {
		System.out.println("HYA");
	}

	@SuppressWarnings({ "serial" })
	private class MotionAction extends AbstractAction implements ActionListener {
		private int deltaX;
		private int deltaY;

		public MotionAction(String name, int deltaX, int deltaY) {
			super(name);

			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}

		public void actionPerformed(ActionEvent e) {
			move(deltaX, deltaY);
		}

	}

	@SuppressWarnings({ "serial" })
	private class ButtonAction extends AbstractAction implements ActionListener {
		String name;
		public ButtonAction(String name) {
			super(name);
			this.name = name;
		}
		
		public void actionPerformed(ActionEvent e) {
			actor.MotionWithKeyBindings c = new actor.MotionWithKeyBindings();
			java.lang.reflect.Method method;
			try {
				method = c.getClass().getMethod(name);
				try {
					method.invoke(c);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}

	public MotionAction addAction(String name, int deltaX, int deltaY) {
		MotionAction action = new MotionAction(name, deltaX, deltaY);

		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(name);
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(pressedKeyStroke, name);
		component.getActionMap().put(name, action);

		return action;

	}
	
	public ButtonAction addAction(String name, String methodName) {
		ButtonAction action = new ButtonAction(methodName);

		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(name);
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(pressedKeyStroke, name);
		component.getActionMap().put(name, action);

		return action;

	}

	public static void addMotionSupport(JComponent component) {
		int delta = 25;
		MotionWithKeyBindings motion = new MotionWithKeyBindings(component);
		motion.addAction("A", -delta, 0);
		motion.addAction("D", delta, 0);
		motion.addAction("W", 0, -delta);
		motion.addAction("S", 0, delta);
		motion.addAction("E", "attack");
	}
}