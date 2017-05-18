package actor;

//Java API imports
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyManager holds all the values of the keys and will tell the player
 * if they are pressing the desired key
 */
public class KeyManager extends KeyAdapter
{
    public boolean[] keys = new boolean[256];
    public boolean esc = false;
    public boolean keyUp = false;
    public boolean keyDown = false;
    public boolean keyRight = false;
    public boolean keyLeft = false;
    public boolean arrowUp = false;
    public boolean arrowDown = false;
    public boolean arrowRight = false;
    public boolean arrowLeft = false;
    public boolean space = false; // pick up
    
   
    public KeyManager()
    {
    }
    
    /**
     * modifies the variables real time
     * 1. set all of the controls to the value in the array of keys
     */
    public void update()
    {
        esc = keys[KeyEvent.VK_ESCAPE];
        keyUp = keys[KeyEvent.VK_W];
        keyDown = keys[KeyEvent.VK_S];
        keyRight = keys[KeyEvent.VK_D];
        keyLeft = keys[KeyEvent.VK_A];
        arrowUp = keys[KeyEvent.VK_UP];
        arrowDown = keys[KeyEvent.VK_DOWN];
        arrowRight = keys[KeyEvent.VK_RIGHT];
        arrowLeft = keys[KeyEvent.VK_LEFT];               
    }
    
    /**
     * When you press a key that key will be set to true
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
        //System.out.println("KeyCode: " + e.getKeyCode());
    }
    
    /**
     * when you release a key it will be set to false
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * not implementing
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    //Getter
    
    public boolean[] getKeys()
    {
        return keys;
    }
    
    public boolean getKeyUp()
    {
        return keyUp;
    }
    
    public boolean getKeyDown()
    {
        return keyDown;
    }
    
    public boolean getKeyRight()
    {
        return keyRight;
    }
    
    public boolean getKeyLeft()
    {
        return keyLeft;
    }
        
    public boolean getSpace()
    {
        return space;
    }
    
    //Setter
    
    public void setKeyUp(boolean keyUp)
    {
        this.keyUp = keyUp;
    }
    
    public void setKeyDown(boolean keyDown)
    {
        this.keyDown = keyDown;
    }
    
    public void setKeyRight(boolean keyRight)
    {
        this.keyRight = keyRight;
    }
    
    public void setKeyLeft(boolean keyLeft)
    {
        this.keyUp = keyLeft;
    }
    
    public void setSpace(boolean space)
    {
        this.space = space;
    }
}


