import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Klasa odpowiedzialna za sortowanie wynikow graczy
 * 
 */

class SortByScore implements Comparator<String>{
    @Override
    /**implementacja komparatora */
    public int compare(String a, String b)
    {
        Integer f =Integer.parseInt(a.split(":  ")[1]);
        Integer k =Integer.parseInt(b.split(":  ")[1]);
        return k - f;
    }

}
