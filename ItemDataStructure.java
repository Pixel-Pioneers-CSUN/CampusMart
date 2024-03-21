import java.util.Iterator;
import java.util.Set;
// this is a Singleton Class meaning only one instance of class
public class ItemDataStructure {
    private static final ItemDataStructure singletonObj = new ItemDataStructure();

    private Set<ItemClass> tree;
    private Iterator<ItemClass> it;

    private ItemDataStructure(){

    }

    // get instance of item structure 
    public static ItemDataStructure getInstance() {
        return singletonObj;
    }
    public Set<ItemClass> getItemDataStructure() {
        return tree;
    }

    public void setItemDataStructure(Set<ItemClass> treeSet) {
        this.tree = treeSet;
    }
    
    
    public Iterator<ItemClass> getIterator() {
        return this.tree.iterator();
    }
    
    // go to next item in list
    

    // restart list


}
