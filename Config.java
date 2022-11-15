import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Klasa odpowiadajaca za wczytywanie z plikow konfiguracyjnych
 * Z pliku "config.txt" wczytywane sa dane, ktore zawsze beda takie same niezaleznie od rozgrywanego poziomu i poziomu trudnosci, miedzy innymi wielkosc okna, liczba duchow i szybkosc Pacmana.
 * Z pliku "diff.txt" wczytywane sa dane, ktore zaleza od wybranego poziomu trudnosci gry, czyli szybkosc z jaka poruszaja sie duchy.
 * Z pliku "levels.txt" wczytywane sa dane, ktore zaleza od rozgrywanego poziomu (labiryntu), miedzy innymi pozycje startowe duchow i pacmana, rozmieszczenie bonusow i dane okreslajace wyglad samego labiryntu.
 * @version 07.12.2020
 */
class Config {
    /** okresla szerokosc okna */
    static int xSize;
    /** okresla wysokosc okna */
    static int ySize;
    /** okresla wymiary kwadratowych blokow, na ktore bedzie dzielone okno */
    static int blockSize;
    /** okresla ilosc blokow w plaszczyznie x */
    static int xNumOfBlocks;
    /** okresla ilosc blokow w plaszczyznie y */
    static int yNumOfBlocks;
    /** okresla szybkosc Pacmana */
    static int pacSpeed;
    /** okresla liczbe przeciwnikow (duchow) */
    static int numOfGhosts;
    /** okresla liczbe przyslugujacych graczowi zyc od poczatku gry*/
    static int numOfLives;
    /** okresla liczbe punktow otrzymanych za zjedzenie malej kropki*/
    static int pointsSmallDot;
    /** okresla liczbe punktow otrzymanych za zjedzenie duzej kropki*/
    static int pointsBigDot;
    /** okresla liczbe punktow otrzymanych za zjedzenie pierwszego ducha*/
    static int pointsGhostOne;
    /** okresla liczbe punktow otrzymanych za zjedzenie drugiego ducha*/
    static int pointsGhostTwo;
    /** okresla liczbe punktow otrzymanych za zjedzenie trzeciego ducha*/
    static int pointsGhostThree;
    /** okresla liczbe punktow otrzymanych za zjedzenie czwartego ducha*/
    static int pointsGhostFour;
    /** okresla liczbe punktow otrzymanych za zjedzenie pierwszego bonusu*/
    static int pointsBonusOne;
    /** okresla liczbe punktow otrzymanych za zjedzenie drugiego bonusu*/
    static int pointsBonusTwo;
    
    /** okresla startowa wspolrzedna x Pacmana*/
    static int xPac;
    /** okresla startowa wspolrzedna y Pacmana*/
    static int yPac;
    /** tablica przechowujaca startowe wspolrzedne y duchow */
    static int[] yGhosts;
    /** tablica przechowujaca startowe wspolrzedne x duchow */
    static int[] xGhosts;
    /** tablica przechowujaca dane na temat labiryntu, w ktorym rozgrywa sie gra */
    static int[] levelInfo;
    /** tablica przechowujaca wspolrzedne x duzych kropek */
    static int[] xBigDots;
    /** tablica przechowujaca wspolrzedne y duzych kropek */
    static int[] yBigDots;
    /** okresla startowa wspolrzedna x pierwszego bonusu */
    static int xBonusOne;
    /** okresla startowa wspolrzedna y pierwszego bonusu */
    static int yBonusOne;
    /** okresla startowa wspolrzedna x drugiego bonusu */
    static int xBonusTwo;
    /** okresla startowa wspolrzedna y drugiego bonusu */
    static int yBonusTwo;
    
    /** okresla predkosc z jaka poruszaja sie duchy */
    static int ghostSpeed;
    
    
    /** Odczytuje dane z plikow konfiguracyjnych, zmienia ich typ i zapisuje do pol w klasie */
    static void loadConfig() throws IOException
    {
    
     InputStream inputStream = new FileInputStream("conf/config.txt");
    
     Properties properties = new Properties();
     properties.load(inputStream);
    
     xSize=Integer.parseInt(properties.getProperty("xSize"));
     ySize=Integer.parseInt(properties.getProperty("ySize"));
     blockSize=Integer.parseInt(properties.getProperty("blockSize"));
     xNumOfBlocks=Integer.parseInt(properties.getProperty("xNumOfBlocks"));
     yNumOfBlocks=Integer.parseInt(properties.getProperty("yNumOfBlocks"));
     pacSpeed=Integer.parseInt(properties.getProperty("pacSpeed"));
     numOfGhosts=Integer.parseInt(properties.getProperty("numOfGhosts"));
     numOfLives=Integer.parseInt(properties.getProperty("numOfLives"));
     pointsSmallDot=Integer.parseInt(properties.getProperty("pointsSmallDot"));
     pointsBigDot=Integer.parseInt(properties.getProperty("pointsBigDot"));
     pointsGhostOne=Integer.parseInt(properties.getProperty("pointsGhostOne"));
     pointsGhostTwo=Integer.parseInt(properties.getProperty("pointsGhostTwo"));
     pointsGhostThree=Integer.parseInt(properties.getProperty("pointsGhostThree"));
     pointsGhostFour=Integer.parseInt(properties.getProperty("pointsGhostFour"));
     pointsBonusOne=Integer.parseInt(properties.getProperty("pointsBonusOne"));
     pointsBonusTwo=Integer.parseInt(properties.getProperty("pointsBonusTwo"));
     //buttonHeight=Integer.parseInt(properties.getProperty("buttonHeight"));
     //buttonWidth=Integer.parseInt(properties.getProperty("buttonWidth"));
     inputStream.close();
    }
    
    /** Odczytuje dane z plikow konfiguracyjnych, zmienia ich typ i zapisuje do pol w klasie 
    * parametr String difficulty oznacza poziom trudnosci - do wyboru "easy", "medium", "hard"
    */
    static int difficultyLoader(String difficulty) throws IOException
    {
     InputStream inputStream = new FileInputStream("conf/diff.txt");
    
     Properties properties = new Properties();
     properties.load(inputStream);
     
     ghostSpeed=Integer.parseInt(properties.getProperty(difficulty));
     inputStream.close();
     return ghostSpeed;
    }
    
    /** Odczytuje dane z plikow konfiguracyjnych, zmienia ich typ i zapisuje do pol i tablic w klasie 
    * parametr levelNumber oznacza numer rozgrywanego poziomu
    */
    static void levelLoader(int levelNumber) throws IOException
    {
     InputStream inputStream = new FileInputStream("conf/levels.txt");
    
     Properties properties = new Properties();
     properties.load(inputStream);
     
     xPac=Integer.parseInt(properties.getProperty("xPac"+Integer.toString(levelNumber)));
     yPac=Integer.parseInt(properties.getProperty("yPac"+Integer.toString(levelNumber)));
     xGhosts=Arrays.stream(properties.getProperty("xGhosts"+Integer.toString(levelNumber)).split("#")).mapToInt(num -> Integer.parseInt(num)).toArray();
     yGhosts=Arrays.stream(properties.getProperty("yGhosts"+Integer.toString(levelNumber)).split("#")).mapToInt(num -> Integer.parseInt(num)).toArray();
     xBigDots=Arrays.stream(properties.getProperty("xBigDots").split("#")).mapToInt(num -> Integer.parseInt(num)).toArray();
     yBigDots=Arrays.stream(properties.getProperty("yBigDots").split("#")).mapToInt(num -> Integer.parseInt(num)).toArray();
     xBonusOne=Integer.parseInt(properties.getProperty("xBonusOne"+Integer.toString(levelNumber)));
     yBonusOne=Integer.parseInt(properties.getProperty("yBonusOne"+Integer.toString(levelNumber)));
     xBonusTwo=Integer.parseInt(properties.getProperty("xBonusTwo"+Integer.toString(levelNumber)));
     yBonusTwo=Integer.parseInt(properties.getProperty("yBonusTwo"+Integer.toString(levelNumber)));
     levelInfo=Arrays.stream(properties.getProperty("levelInfo").split("#")).mapToInt(num -> Integer.parseInt(num)).toArray(); 
     inputStream.close();
    }
    
    }
