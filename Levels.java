import java.io.IOException;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.io.File;

/**
 * Klasa odpowiadajaca za rysowanie
 */
public class Levels extends JPanel
{
    /** obecna wspolrzedna x Pacmana */
    static int currXPac;
    /** obecna wspolrzednia y Pacmana */
    static int currYPac;
    
    /** obecna wspolrzednia x Ducha nr 1 */
    static int currXG1;
    /** obecna wspolrzednia y Ducha nr 1 */
    static int currYG1;

    /** obecna wspolrzednia x Ducha nr 2 */
    static int currXG2;
    /** obecna wspolrzednia y Ducha nr 2 */
    static int currYG2;

    /** obecna wspolrzednia x Ducha nr 3 */
    static int currXG3;
    /** obecna wspolrzednia y Ducha nr 3 */
    static int currYG3;

    /** obecna wspolrzednia x Ducha nr 4 */
    static int currXG4;
    /** obecna wspolrzednia x Ducha nr 4 */
    static int currYG4;
    
    /** zmienna zliczajaca punkty */ 
    static int SCORE = 0;

    /** zmienna zliczajaca zycia */
    static int lives = Config.numOfLives;
    
    /** zmienne potrzebne do bonusu nr 1 */
    boolean bonus1 = false;
    /**  zmienna potrzebna do dzialania bonusu nr 1 */
    int bonus1_counter = 0;
    /**  zmienna potrzebna do dzialania bonusu nr 1 */
    int bonus_speed = 2;
    /**  zmienna potrzebna do dzialania bonusu nr 1 */
    int bonus1_position = (Config.xBonusOne / Config.blockSize) + ((Config.yBonusOne/Config.blockSize) * Config.xNumOfBlocks);


    /**  zmienna potrzebna do dzialania bonusu nr 2 */
    boolean bonus2 = false;
    /**  zmienna potrzebna do dzialania bonusu nr 2 */
    int bonus2_counter = 0;  
    /**  zmienna potrzebna do dzialania bonusu nr 2 */
    int bonus2_position = (Config.xBonusTwo / Config.blockSize) + ((Config.yBonusTwo/Config.blockSize) * Config.xNumOfBlocks);

    /** rysuje poziom */
    public void paintComponent(Graphics g)
    {        

        String fileSeparator = File.separator;
        int a=0;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        /** narysowanie tła planszy */
        g2d.fillRect(0,0,Config.xSize,Config.ySize);
        g2d.setStroke(new BasicStroke(7));
        for (int y=0; y<Config.ySize;y=y+Config.blockSize)
        {
            for(int x=0;x<Config.xSize;x=x+Config.blockSize)
            {
             if(Config.levelInfo[a]==5)
             {
                if(!bonus2 && bonus2_counter <= 401){
                    g2d.setColor(Color.blue);
                    /** narysowanie bloku */
                    g2d.fillRect(x,y,Config.blockSize,Config.blockSize);
                }
                else{
                    g2d.setColor(Color.white);
                    /** narysowanie bloku */
                    g2d.fillRect(x,y,Config.blockSize,Config.blockSize);
                }
             }
             if(Config.levelInfo[a]==7)
             {
                g2d.setColor(Color.white);
                /** narysowanie malej kropki */
                g2d.fillOval(x+15,y+15,6,6);
             }
             if(Config.levelInfo[a] == 10){
                g2d.setColor(Color.black); 
             }
             if(Config.levelInfo[a]==0)
             {
                g2d.setColor(Color.green);
                /** narysowanie granicy planszy w pionie */
                g2d.drawLine(x+Config.blockSize/2,y,x+Config.blockSize/2,y+Config.blockSize);
             }
             if(Config.levelInfo[a]==8)
             {
                g2d.setColor(Color.green);
                /** narysowanie granicy planszy w poziomie*/
                g2d.drawLine(x,y+Config.blockSize/2,x+Config.blockSize,y+Config.blockSize/2);
             }
             if(Config.levelInfo[a]==1)
             {
                g2d.setColor(Color.green);
                g2d.drawLine(x+Config.blockSize/2,y+Config.blockSize/2,x+Config.blockSize/2,y+Config.blockSize);
                g2d.drawLine(x+Config.blockSize/2,y+Config.blockSize/2,x+Config.blockSize,y+Config.blockSize/2);
             }
             if(Config.levelInfo[a]==2)
             {
                g2d.setColor(Color.green);
                g2d.drawLine(x,y+Config.blockSize/2,x+Config.blockSize/2,y+Config.blockSize/2);
                g2d.drawLine(x+Config.blockSize/2,y+Config.blockSize/2,x+Config.blockSize/2,y+Config.blockSize);
             }
             if(Config.levelInfo[a]==3)
             {
                g2d.setColor(Color.green);
                g2d.drawLine(x+Config.blockSize/2,y,x+Config.blockSize/2,y+Config.blockSize/2);
                g2d.drawLine(x+Config.blockSize/2,y+Config.blockSize/2,x+Config.blockSize,y+Config.blockSize/2);
             }
             if(Config.levelInfo[a]==4)
             {
                g2d.setColor(Color.green);
                g2d.drawLine(x,y+Config.blockSize/2,x+Config.blockSize/2,y+Config.blockSize/2);
                g2d.drawLine(x+Config.blockSize/2,y,x+Config.blockSize/2,y+Config.blockSize/2);
             }
             a++;
            }
        }
        
        g2d.setColor(Color.white);
        ///** narysowanie pierwszego duzej kropki */
        //g2d.fillOval(Config.xBigDots[0]+12,Config.yBigDots[0]+12,12,12);
        ///** narysowanie drugiej duzej kropki */
        //g2d.fillOval(Config.xBigDots[1]+12,Config.yBigDots[1]+12,12,12);
        ///** narysowanie trzeciej duzej kropki */
        //g2d.fillOval(Config.xBigDots[2]+12,Config.yBigDots[2]+12,12,12);
        ///** narysowanie czwartej duzej kropki */
        //g2d.fillOval(Config.xBigDots[3]+12,Config.yBigDots[3]+12,12,12);
        
        /** przygotowanie pacmana */
        if(Controls.yp<0)
        {
         Image up = new ImageIcon("Graphics" +fileSeparator + "pacu.gif").getImage();
         g2d.drawImage(up,currXPac-7,currYPac-7,this);
        }
        
        else if(Controls.yp>0)
        {
         Image down = new ImageIcon("Graphics" +fileSeparator + "pacd.gif").getImage();
         g2d.drawImage(down,currXPac-7,currYPac-7,this);
        }
        
        else if(Controls.xp<0)
        {
         Image left = new ImageIcon("Graphics" +fileSeparator + "pacl.gif").getImage();
         g2d.drawImage(left,currXPac-7,currYPac-7,this);
        }
        
        else
        {
         Image right = new ImageIcon("Graphics" +fileSeparator + "pac.gif").getImage();
         g2d.drawImage(right,currXPac-7,currYPac-7,this);
        }
        
        if(!bonus1 && bonus1_counter<400){
        Image cherry = new ImageIcon("Graphics" +fileSeparator + "cherry.png").getImage();
         /** narysowanie pierwszego bonusu */
         g2d.drawImage(cherry,Config.xBonusOne+5,Config.yBonusOne+5,this);
        }
        if(!bonus2 && bonus2_counter<400){
        Image strawberry = new ImageIcon("Graphics" +fileSeparator + "strawberry.png").getImage();
        /** narysowanie drugiego bonusu */
        g2d.drawImage(strawberry,Config.xBonusTwo+5,Config.yBonusTwo+5,this);
        }
        Image rGhost = new ImageIcon("Graphics" +fileSeparator + "rGhost.gif").getImage();
        /** narysowanie pierwszego ducha  */
        g2d.drawImage(rGhost,currXG1,currYG1,this);
        
        
        Image oGhost = new ImageIcon("Graphics" +fileSeparator + "oGhost.gif").getImage();
        /** narysowanie drugiego ducha */
        g2d.drawImage(oGhost,currXG2,currYG2,this);

        Image bGhost = new ImageIcon("Graphics" +fileSeparator + "bGhost.gif").getImage();
        /** narysowanie trzeciego ducha */
        g2d.drawImage(bGhost,currXG3,currYG3,this);
        
        Image pGhost = new ImageIcon("Graphics" +fileSeparator + "pGhost.gif").getImage();
        /** narysowanie czwartego ducha */
        g2d.drawImage(pGhost,currXG4,currYG4,this);
        
        String s = "SCORE: " +SCORE;
        g2d.setFont(new Font("Monospaced", Font.BOLD, Config.blockSize/2)); 
        /** narysowanie wyniku gracza  */
        g2d.drawString(s,Config.xSize-125,Config.ySize-20);
        
        Image heart = new ImageIcon("heart.png").getImage();
        for (int i=0;i<lives;i++)
        {
            /** narysowanie serca oznaczającego jedno życie  */
            g2d.drawImage(heart,20+i*21,Config.ySize-35,this);
        }
        
    }


    /**Metoda odpowiadajaca za animacje poruszanie sie Pacamana i Duszkow po planszy */
    public void animation() throws IOException
    {
        currXPac = Config.xPac;
        currYPac = Config.yPac;

        currXG1=Config.xGhosts[0];
        currYG1=Config.yGhosts[0];
        int dx1 = 0;
        int dy1 = 0;

        currXG2=Config.xGhosts[1];
        currYG2=Config.yGhosts[1];
        int dx2 = 0;
        int dy2 = 0;

        currXG3=Config.xGhosts[2];
        currYG3=Config.yGhosts[2];
        int dx3 = 0;
        int dy3 = 0;

        currXG4=Config.xGhosts[3];
        currYG4=Config.yGhosts[3];
        int dx4 = 0;
        int dy4 = 0;



        Random random = new Random();
        int count = 0;

        int maxScore=0;
        for(int i =0; i<Config.levelInfo.length;i++)
        {
            if (Config.levelInfo[i]==7)
            {
                maxScore+=10;
            }
        }
        
        maxScore+=250;

        /**Petla regulujaca zachowanie postaci */
        while(lives>0)
        {

            /**przeliczenie pozycji pacmana z planszy 2d na tabele 1d jaka jest nasz leveInfo*/
            int posPac = (currXPac)/Config.blockSize + (((currYPac) / Config.blockSize)*Config.xNumOfBlocks); 
            int posG1 = (currXG1 + (Config.blockSize/2)) /Config.blockSize + ((   (currYG1 + (Config.blockSize/2)) / Config.blockSize)*Config.xNumOfBlocks); 
            int posG2 = (currXG2 + (Config.blockSize/2)) /Config.blockSize + ((   (currYG2 + (Config.blockSize/2)) / Config.blockSize)*Config.xNumOfBlocks); 
            int posG3 = (currXG3 + (Config.blockSize/2)) /Config.blockSize + ((   (currYG3 + (Config.blockSize/2)) / Config.blockSize)*Config.xNumOfBlocks); 
            int posG4 = (currXG4 + (Config.blockSize/2)) /Config.blockSize + ((   (currYG4 + (Config.blockSize/2)) / Config.blockSize)*Config.xNumOfBlocks); 

            if(currXPac+Config.blockSize+Controls.xp>Config.xSize+11-Config.blockSize||currXPac+Controls.xp<0+Config.blockSize)
            {
                Controls.xp=0;
                
            }
            if(currYPac+Controls.yp+Config.blockSize>Config.ySize+11-Config.blockSize||currYPac+Controls.yp<0+Config.blockSize)
            {
                Controls.yp=0;
                
            }
            
            if(!bonus2){
                /**UNIKANIE PRZESZKOD NA BOKI przez PACMANA*/
                if(  (Config.levelInfo[posPac] == 5 && Controls.xp == -1) ){
                
                    Controls.xp = 0;
                     
                    currXPac+=7;
                        
                    if(Controls.yp == -1){
                    Controls.yp = -1;
                    }
                    if(Controls.yp == 1){
                    Controls.yp = 1;
                    }

                }
                
                if(Config.levelInfo[posPac] == 5  && Controls.xp == 1)
                {
                    Controls.xp = 0;
                    currXPac-=15;
                    if(Controls.yp == 1){
                        Controls.yp = 1;
                    }
                    if(Controls.yp == -1){
                        Controls.yp = -1;
                    }
                }

                //UNIKANIE PRZESZKOD GORA-DOL przez PACMANA
                if( Config.levelInfo[posPac ] == 5 && Controls.yp == 1) 
                   {
                    Controls.yp = 0;
     
                    currYPac-=15;

                    if(Controls.xp == 1){
                        Controls.xp = 1;
                         }
                    if(Controls.xp == -1){
                        Controls.xp = -1;
                        }
                    }
                    if( Config.levelInfo[posPac] == 5 && Controls.yp == -1)
                    {
                    Controls.yp = 0;
     
                    currYPac+=7;

                    if(Controls.xp == 1){
                          Controls.xp = 1;
                         }
                    if(Controls.xp == -1){
                          Controls.xp = -1;
                         }
                    }
                 
            }
            else if (bonus2 && bonus2_counter<400){
                bonus2_counter++;
                //System.out.println(bonus2_counter);
            }

            /**generowanie losowych ruchy duszkow */
            if( count%Config.blockSize == 0 ){
                dx1 = 0;
                dy1 = 0;
                dx2 = 0;
                dy2 = 0;
                dx3 = 0;
                dy3 = 0;
                dx4 = 0;
                dy4 = 0;
                int g1_move = random.nextInt(4)+1;
                if(g1_move == 1){
                    dx1 = 1;
                } 
                else if(g1_move == 2){
                    dx1 = -1;
                } 
                else if(g1_move == 3){
                    dy1 = 1;
                }
                else if(g1_move == 4){
                    dy1 = -1;
                }

                int g2_move = random.nextInt(4)+1;
                if(g2_move == 1){
                    dx2 = 1;
                } 
                else if(g2_move == 2){
                    dx2 = -1;

                } 
                else if(g2_move == 3){
                    dy2 = 1;
                }
                else if(g2_move == 4){
                    dy2 = -1;
                } 
                int g3_move = random.nextInt(4)+1;
                if(g3_move == 1){
                    dx3 = 1;
                } 
                else if(g3_move == 2){
                    dx3 = -1;
                } 
                else if(g3_move == 3){
                    dy3 = 1;
                }
                else if(g3_move == 4){
                    dy3 = -1;
                }
                int g4_move = random.nextInt(4)+1;
                if(g4_move == 1){
                    dx4 = 1;
                } 
                else if(g4_move == 2){
                    dx4 = -1;

                } 
                else if(g4_move == 3){
                    dy4 = 1;
                }
                else if(g4_move == 4){
                    dy4 = -1;
                }
                count = 0;
            } 


           

            count++;
           /**mechanizm odbijania sie duszkow od scian */
            if( ( Config.levelInfo[posG1+1] == 5 || Config.levelInfo[posG1+1] == 0  /*&& dx1 == 1*/) // przeszkody prawo-lewo
                || (Config.levelInfo[posG1] == 5 || Config.levelInfo[posG1] == 0 /*&& dx1 == -1*/) ){                
                dx1 = -1*dx1;
            }
            if( (Config.levelInfo[posG1 + Config.xNumOfBlocks] == 5 || Config.levelInfo[posG1 + Config.xNumOfBlocks] == 8 /*&& dy1 == 1*/) //przeszkody gora-dol
                || ( Config.levelInfo[posG1] == 5 || Config.levelInfo[posG1] == 8/*&& dy1 == -1*/)){                
                dy1 = -1*dy1;
            }  
 
            if( (Config.levelInfo[posG2+1] == 5 || Config.levelInfo[posG2+1] == 0  /*&& dx2 == 1*/) 
                || (Config.levelInfo[posG2] == 5 || Config.levelInfo[posG2] == 0 /*&& dx2 == -1*/) ){                
                dx2 = -1*dx2;
            }
            if( (Config.levelInfo[posG2 + Config.xNumOfBlocks] == 5 || Config.levelInfo[posG2 + Config.xNumOfBlocks] == 8 /*&& dy2 == 1*/) 
                || ( Config.levelInfo[posG2] == 5 || Config.levelInfo[posG2] == 8 /*&& dy2 == -1*/)){                
                dy2 = -1*dy2;
            }  

            if( (Config.levelInfo[posG3+1] == 5 || Config.levelInfo[posG3+1] == 0  /*&& dx3 == 1*/) 
                || (Config.levelInfo[posG3] == 5 || Config.levelInfo[posG3] == 0 /*&& dx3 == -1*/) ){                
                dx3 = -1*dx3;
            }
            if( (Config.levelInfo[posG3 + Config.xNumOfBlocks] == 5 || Config.levelInfo[posG3 + Config.xNumOfBlocks] == 8 /*&& dy3 == 1*/) 
                || ( Config.levelInfo[posG3] == 5 || Config.levelInfo[posG3] == 8 /*&& dy3 == -1*/)){                
                dy3 = -1*dy3;
            }  

            if( (Config.levelInfo[posG4+1] == 5 || Config.levelInfo[posG4+1] == 0  /*&& dx4 == 1*/) 
                || (Config.levelInfo[posG4] == 5 || Config.levelInfo[posG4] == 0 /*&& dx4 == -1*/) ){                
                dx4 = -1*dx4;
            }
            if( (Config.levelInfo[posG4 + Config.xNumOfBlocks] == 5 || Config.levelInfo[posG4 + Config.xNumOfBlocks] == 8 /*&& dy4 == 1*/) 
                || ( Config.levelInfo[posG4] == 5 || Config.levelInfo[posG4] == 8 /*&& dy4 == -1*/)){                
                dy4 = -1*dy4;
            }   

            currXG1 = currXG1 + Controls.ghostSpeed*dx1;
            currYG1 = currYG1 + Controls.ghostSpeed*dy1;
            currXG2 = currXG2 + Controls.ghostSpeed*dx2;
            currYG2 = currYG2 + Controls.ghostSpeed*dy2;
            currXG3 = currXG3 + Controls.ghostSpeed*dx3;
            currYG3 = currYG3 + Controls.ghostSpeed*dy3;
            currXG4 = currXG4 + Controls.ghostSpeed*dx4;
            currYG4 = currYG4 + Controls.ghostSpeed*dy4;

           /**aktywowanie bonusu nr 1 */
            if(posPac == bonus1_position && bonus1_counter<400){
                bonus1 = true; 
                
            }else if(bonus1_counter == 400){
                bonus1 = false; 
                SCORE += Config.pointsBonusOne;
                bonus1_counter +=1;
            }

            /** funkcjonowanie bonusu 1 */
            if(!bonus1){
                currXPac=currXPac+Controls.xp;
                currYPac=currYPac+Controls.yp;
            }else if(bonus1){
                currXPac=currXPac+bonus_speed*Controls.xp;
                currYPac=currYPac+bonus_speed*Controls.yp;
                bonus1_counter++;
            }
    
            /**zbieranie punktow */
            if(Config.levelInfo[posPac] == 7 ){
                SCORE+=Config.pointsSmallDot;
                Config.levelInfo[posPac] = 10; 
                //lives--;
            }



            /** aktywowanie bonusu nr 2 */
            if(posPac == bonus2_position && bonus2_counter<400){
                bonus2 = true;
                
            }else if(bonus2_counter == 400){
                bonus2 = false;
                SCORE += Config.pointsBonusTwo;
                bonus2_counter +=1;
            }
                   
            /**mechanizm kolizji pacmana z duszkami */
            if( ( (posPac == posG1) || (posPac == posG2) || (posPac == posG3) || (posPac == posG4) ) ){

                currXPac = Config.xPac;
                currYPac = Config.yPac;
                lives--;
            }
            
            
            if (SCORE==maxScore)
            {lives=0;}

            repaint();           
            try
            {
                Thread.sleep(8);
            }
            catch(InterruptedException e)
            {
            }
            
            
            
        }
        if (lives==0)
            { 
            try 
            {
                Ranking.saveScores(SCORE,Menu.nick);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
                
                lives=3;
                Menu m = new Menu();
                m.finishMenu();
                Main window = new Main();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setSize(Config.xSize, Config.ySize);
                window.add(m);
                window.setVisible(true);
                SCORE=0;
                
                    try 
                    {
                        Thread.sleep(20000);
                    } 
                    catch (InterruptedException e)  
                    {
                        Thread.currentThread().interrupt();       
                    }
                
                window.dispose();

                int b =1;
                Config.levelLoader(b);
                Levels lev = new Levels();
                Main window2 = new Main();
                window2.add(lev);
                window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window2.setSize(Config.xSize+Config.blockSize,Config.ySize+Config.blockSize);
                window2.setVisible(true);
                window2.addKeyListener(new Controls());
                lev.animation();
                
            }
        
    }
}