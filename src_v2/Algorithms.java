import java.util.ArrayList;

public class Algorithms {
    
    public static Cache CacheWithId(ArrayList<Cache> caches, int id) {
        // We suppose that there is a cache with that id in the list
        for (Cache c : caches) {
            if (c.id == id) {
                return c;
            }
        }
        
        // Nonexistent case
        return null;
    }
    
    public static boolean contains(ArrayList<Cache> caches, int id) {
        for (Cache c : caches) {
            if (c.id == id) {
                return true;
            }
        }
        return false;
    }
    

}
