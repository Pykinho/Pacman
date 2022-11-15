import java.io.IOException;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/**
 * Klasa umozliwiajaca sprawdzenie czy metody klasy Config dzialaja poprawnie.
 * W finalnej wersji programu bedzie dzialala inaczej.
 * @version 08.12.2020
 */
public class Main extends JFrame {

    
public static void main(String[] args) throws IOException
{
    
  int a =1;
  Config.levelLoader(a);
  Config.loadConfig();

  Menu m = new Menu();
  m.mainMenu();
  Main window = new Main();
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  window.setSize(Config.xSize, Config.ySize);
  window.add(m);
  window.setVisible(true);
  

   
   try {
        Thread.sleep(20000);
       } 
       catch (InterruptedException e)  
       {
         Thread.currentThread().interrupt();       
       }
   
  window.dispose();    
   
  Menu.start=false;
  Levels lev = new Levels();
   
  Main window2 = new Main();
  window2.addKeyListener(new Controls());
  window2.add(lev);
  window2.setSize(Config.xSize+Config.blockSize,Config.ySize+Config.blockSize);
  window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  window2.setVisible(true);
   
  lev.animation();

}
}
