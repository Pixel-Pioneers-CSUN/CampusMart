
// this is a Singleton Class meaning only one instance of class

import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents a singleton data structure for storing items.
 */
public class ItemDataStructure {
    private static final ItemDataStructure singletonObj = new ItemDataStructure();

    private HashMap<Integer, ItemClass> map;

    private ItemDataStructure(){
        // Private constructor to prevent instantiation

    }
    /**
     * Returns the instance of the ItemDataStructure.
     *
     * @return The singleton instance of ItemDataStructure.
     */ 
    public static ItemDataStructure getInstance() {
        return singletonObj;
    }
    
    /**
     * Returns the item data structure.
     *
     * @return The item data structure.
     */
    public HashMap<Integer, ItemClass> getItemDataStructure() {
        return map;
    }
    
    /**
     * Sets the item data structure.
     *
     * @param hashMap The item data structure to set.
     */
    public void setItemDataStructure(HashMap<Integer, ItemClass> hashMap) {
        this.map = hashMap;
    }
    /**
     * Returns an iterator over the item data structure entries.
     *
     * @return An iterator over the item data structure entries.
     */
    public Iterator<HashMap.Entry<Integer, ItemClass>> getIterator() {
        return map.entrySet().iterator();

    }

}
