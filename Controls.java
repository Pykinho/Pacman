import java.awt.event.*;
import javax.swing.Timer;
/**
Klasa odpowiadajaca za sterowanie pacmanem
 */
public class Controls implements KeyListener {
    /** okresla zmiane polozenia bohatera w pionie */
    static int xp;
    /** okresla zmiane polozenia bohatera w poziomie */
    static int yp;
    
    /**okresla predkosc duchow*/
    static int ghostSpeed =1;

    
    /** odczytuje wcisniete przyciski na klawiaturze */
    public void keyPressed(KeyEvent e)
    {
        int pushButton = e.getKeyCode();
        if(pushButton==KeyEvent.VK_UP)
        {
            xp = 0;
            yp = -Config.pacSpeed;
            ghostSpeed = Menu.ghostS;
        }
        if(pushButton==KeyEvent.VK_DOWN)
        {
            xp = 0;
            yp = Config.pacSpeed;
            ghostSpeed = Menu.ghostS;
        }
        if(pushButton==KeyEvent.VK_LEFT)
        {
            xp = -Config.pacSpeed;
            yp = 0;
            ghostSpeed = Menu.ghostS;
        }
        if(pushButton==KeyEvent.VK_RIGHT)
        {
            xp = Config.pacSpeed;
            yp = 0;
            ghostSpeed = Menu.ghostS;
        }
        if (pushButton==KeyEvent.VK_SPACE)
        {
            xp=0;
            yp=0;
            ghostSpeed=0;
        }
        
    }
    public void keyReleased(KeyEvent e)
    {}
    
    public void keyTyped(KeyEvent e)
    {}
}