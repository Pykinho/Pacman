import java.io.InputStream;
import java.util.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;

/**
 * 
 * Klasa odpowiedzialna za ranking najlepszych wyników - zapis i odczyt
 */
public class Ranking{
    
    /** przechowuje najlepsze wyniki */
    static ArrayList<String> topScores = new ArrayList<String>();    
    
    /** Umozliwia odczyt wynikow zapisanych w pliku tekstowym */
    static String readScores(int index) throws IOException
    {
     String fileSeparator = File.separator;
     InputStream inputStream = new FileInputStream("conf" + fileSeparator + "Ranking.txt");
     Properties properties = new Properties();
     properties.load(inputStream);
     
     topScores = new ArrayList<String>();
     
     for(int i = 0;i<5;i++)
     {  
         topScores.add(properties.getProperty("name"+i)+":  "+properties.getProperty("score"+i));
     }
     
     inputStream.close();
     
     return topScores.get(index);
    }
    
    /** Umozliwia zapis najlepszych wynikow do pliku, odczytuje wyniki z pliku, dodaje wynik aktualny, sortuje i usuwa wynik spoza top 5 */
    static void saveScores(int currentScore,String nick) throws IOException
    {
        for (int i=0;i<5;i++)
        {
           readScores(i);   
        }
        topScores.add(nick+":  "+currentScore);
        topScores.sort(new SortByScore());
        topScores.remove(5);
        String fileSeparator = File.separator;
        InputStream inputStream = new FileInputStream("conf" + fileSeparator + "Ranking.txt");
        OutputStream outputStream = new FileOutputStream("conf" + fileSeparator + "Ranking.txt");
        Properties properties = new Properties();
        properties.load(inputStream);
        
        for (int i=0;i<5;i++)
        {
            properties.setProperty("name"+i,topScores.get(i).split(":  ")[0]);
            properties.setProperty("score"+i,topScores.get(i).split(":  ")[1]);
        }
        
        properties.store(outputStream,null);
        inputStream.close();
    }

}
