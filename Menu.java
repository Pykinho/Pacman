import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
/**
 * Klasa odpowiedzialna za wyświetlanie i działanie menu gry - w tym okna z najlepszymi wynikami, okna z poziomem trudnosci i okna z wpisaniem nazwy uzytkownika

 */
public class Menu extends JPanel{
    
    /**przechowuje wpisany przez gracza nick*/
    static String nick;
    
    public int currLevel;
    
    /**przechowuje uzyskany przez gracza wynik */
    static int score = Levels.SCORE;
    
    /**przechowuje uzyskany przez gracza wynik */
    private static String difficulty = "easy";
    
    /**przechowuje wybrana przez gracza predkosc duchow */
    public static int ghostS;
    
    /**przechowuje stan gry - czy rozpoczeta czy nie */
    public static boolean start;
    
    /**przypisuje domyslna predkosc duchow */
    public Menu() throws IOException
    {
      ghostS = Config.difficultyLoader(difficulty);
    }
    
    /**Metoda odpowiadajaca za wyswietlenie i dzialanie glownego menu */
    void mainMenu() throws IOException
    {
        //this.getContentPane().removeAll();
        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill=GridBagConstraints.NONE;
        
        JLabel gameLabel = new JLabel("PAC - MAN");
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 0;
        this.add(gameLabel, c);
        
        ImageIcon pacImage = new ImageIcon("Graphics/pac1.png");
        JLabel pacLabel = new JLabel(pacImage);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight=4;
        this.add(pacLabel, c);
        
        JButton startButton = new JButton("NOWA GRA");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight=1;
        this.add(startButton, c);
        
        JButton quitButton = new JButton("WYJŚCIE");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 4;
        c.gridheight=1;
        this.add(quitButton, c);
        
        JButton scoresButton = new JButton("TOP WYNIKI");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 2;
        c.gridheight=1;
        this.add(scoresButton, c);
        
        JButton difficultyButton = new JButton("TRUDNOŚĆ");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 3;
        c.gridheight=1;
        this.add(difficultyButton, c);
        
        
        ImageIcon ghostImage = new ImageIcon("Graphics/Gho1.png");
        JLabel ghostLabel = new JLabel(ghostImage);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight=4;
        this.add(ghostLabel, c);
        
        
        quitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        
        startButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                
                try 
            {
                nickMenu(); 
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
                
            }
        });
        
        scoresButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                
                try 
            {
                topScores(); 
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            }
        });
        
        difficultyButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                difficultyChoice();
            }
        });
        

        gameLabel.setFont(new Font("Monospaced", Font.BOLD, 2*Config.blockSize)); 
        startButton.setBackground(Color.YELLOW);
        scoresButton.setBackground(Color.YELLOW);
        quitButton.setBackground(Color.YELLOW);
        difficultyButton.setBackground(Color.YELLOW);
        gameLabel.setForeground(Color.YELLOW);
        //this.getContentPane().setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);
        this.validate();
        this.repaint();
        this.setVisible(true);
    }
    
    /**metoda odpowiadajaca za dzialanie menu, w ktorym wpisuje sie nick*/
    public void nickMenu() throws IOException
    {   
        //this.getContentPane().removeAll();
        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill=GridBagConstraints.NONE;
        
        
        JLabel gameLabel = new JLabel("PAC - MAN");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(gameLabel, c);
        
        JLabel nameLabel = new JLabel("PODAJ SWOJE IMIĘ");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        this.add(nameLabel, c);
        
        TextField nickField = new TextField("Player");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 2;
        this.add(nickField, c);
        
        JButton backButton = new JButton("WSTECZ");
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 3;
        this.add(backButton, c);
        
        JButton acceptButton = new JButton("ZATWIERDŹ");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 3;
        this.add(acceptButton, c);
        
        
        acceptButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                nick = nickField.getText();
                start=true;
                 
            }
        });
        
        
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                //mainMenu();
            try 
            {
                mainMenu();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            }
        });

        nameLabel.setFont(new Font("Monospaced", Font.BOLD, Config.blockSize)); 

        gameLabel.setFont(new Font("Monospaced", Font.BOLD, 2*Config.blockSize)); 
        acceptButton.setBackground(Color.YELLOW);
        backButton.setBackground(Color.YELLOW);
        nickField.setBackground(Color.YELLOW);
        nameLabel.setForeground(Color.YELLOW);
        gameLabel.setForeground(Color.YELLOW);
       // this.getContentPane().setBackground(Color.BLACK);
       this.setBackground(Color.BLACK);

        this.validate();
        this.repaint();
        this.setVisible(true);
    }
    
    /**metoda odpowiedzialna za dzialanie okna, ktore wyswietla sie po zakonczeniu gry */
    public void finishMenu()
    {
        this.removeAll();
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill=GridBagConstraints.NONE;
        
        JLabel gameLabel = new JLabel("PAC - MAN");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(gameLabel, c);
        
        JLabel scoreLabel = new JLabel("KONIEC GRY!");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        this.add(scoreLabel, c);
        
        JLabel congratsLabel = new JLabel("GRATULACJE "+nick+"!");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 2;
        this.add(congratsLabel, c);
        
        JButton backButton = new JButton("MENU GLOWNE");
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 3;
        this.add(backButton, c);
        
        JButton acceptButton = new JButton("WYJDZ");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 3;
        this.add(acceptButton, c);
        
        acceptButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        
        
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
             
            try 
            {
                mainMenu();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            }
        });
        
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, Config.blockSize));
        congratsLabel.setFont(new Font("Monospaced", Font.BOLD, Config.blockSize)); 
        gameLabel.setFont(new Font("Monospaced", Font.BOLD, 2*Config.blockSize)); 
        scoreLabel.setForeground(Color.YELLOW);
        gameLabel.setForeground(Color.YELLOW);
        congratsLabel.setForeground(Color.YELLOW);
        
        acceptButton.setBackground(Color.YELLOW);
        backButton.setBackground(Color.YELLOW);
        this.setBackground(Color.BLACK);
        
        this.validate();
        this.repaint();
        this.setVisible(true);
        //this.getContentPane().setBackground(Color.BLACK);
        
    }
    
    /**metoda odpowiedzialna za wyswietlenie i dzialanie okna z najlepszymi wynikami */
    public void topScores() throws IOException
    
    {
       // this.getContentPane().removeAll();
       this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill=GridBagConstraints.NONE;
        
        JLabel gameLabel = new JLabel("PAC - MAN");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(gameLabel, c);
        
        JLabel scoreLabel = new JLabel("NAJLEPSZE WYNIKI");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        this.add(scoreLabel, c);
        
        for (int i=0;i<5;i++)
        {
        JLabel topScoresLabel = new JLabel(Ranking.readScores(i));
        c.gridx = 0; 
        c.gridwidth = 2;
        c.gridy = 2+i;
        this.add(topScoresLabel, c);
        topScoresLabel.setForeground(Color.YELLOW);
       }
        
        JButton backButton = new JButton("WSTECZ");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 7;
        this.add(backButton, c);
        
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                //mainMenu();
                try 
            {
                mainMenu();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            }
        });
        
        gameLabel.setFont(new Font("Monospaced", Font.BOLD, 2*Config.blockSize)); 
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD,Config.blockSize)); 
        //this.getContentPane().setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);
        backButton.setBackground(Color.YELLOW);
        scoreLabel.setForeground(Color.YELLOW);
        gameLabel.setForeground(Color.YELLOW);
        

        this.validate();
        this.repaint();
        this.setVisible(true);
    }
    
    /**metoda odpowiedzialna za wyswietlenie i dzialanie okna, w ktorym gracz wybiera poziom trudnosci*/
    void difficultyChoice()
    {
        //this.getContentPane().removeAll();
        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill=GridBagConstraints.NONE;
        
        JLabel gameLabel = new JLabel("PAC - MAN");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(gameLabel, c);
        
        JLabel difficultyLabel = new JLabel("WYBÓR POZIOMU TRUDNOŚCI");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        this.add(difficultyLabel, c);
        
        String diffs[]={"easy","medium","hard"};
        JComboBox d=new JComboBox(diffs); 
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 2;
        this.add(d, c);
        
        JButton backButton = new JButton("WSTECZ");
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 3;
        this.add(backButton, c);
        
        JButton acceptButton = new JButton("ZATWIERDŹ");
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 3;
        this.add(acceptButton, c);
        
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                //mainMenu();
                try 
            {
                mainMenu();
                
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            }
        });
        
        d.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                difficulty = ""+d.getItemAt(d.getSelectedIndex());
            }
        });
        
        acceptButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                try{
                  
                  ghostS = Config.difficultyLoader(difficulty);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
         });      
                
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event)
            {
                //mainMenu();
                try 
            {
                mainMenu();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            }
        });
            
        
        gameLabel.setFont(new Font("Monospaced", Font.BOLD, 2*Config.blockSize)); 
        difficultyLabel.setFont(new Font("Monospaced", Font.BOLD,Config.blockSize)); 
        
        difficultyLabel.setForeground(Color.YELLOW);
        gameLabel.setForeground(Color.YELLOW);
        d.setBackground(Color.YELLOW);
        acceptButton.setBackground(Color.YELLOW);
        backButton.setBackground(Color.YELLOW);
        this.setBackground(Color.BLACK);
        
        this.validate();
        this.repaint();
        this.setVisible(true);
    }
}

