import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ItemDisplayController {
    @FXML
    private Text ItemNameDisplay;

    @FXML
    private Text itemPriceDisplay;

    @FXML
    private Button myButton;

    @FXML
    private ImageView myImageView;
    
    
    //Image myImage = new Image(getClass().getResourceAsStream("projectPhotos/soda.png"));
    
    public DatabaseUtility bd;
    
    
    
    @FXML
    void displayItemPhoto(ActionEvent event) throws InterruptedException {
        // System.out.print("Hello");
        // myImageView.setImage(myImage);

        // i Need this to be dont outside of the button press/ image show
        DatabaseUtility bd = new DatabaseUtility();
        bd.setTable("itemtable");
        Set<ItemClass> tree = new TreeSet<ItemClass>(bd.createDataStructureItemClass());
        //System.out.println(tree);
        Iterator<ItemClass> it = tree.iterator();
        ItemClass temp = new ItemClass();

        while(it.hasNext()) {
            temp = it.next();
            temp.printItem();
            ItemNameDisplay.setText(temp.getItemName());
            itemPriceDisplay.setText(temp.getPrice().toString());
            Image myImage = new Image(getClass().getResourceAsStream("projectPhotos/" + temp.getItemPicture()));
            myImageView.setImage(myImage);
            
            
        }
    }

    // create a way to display all info and item page 
    // basic to just work
    // Need to make it so it can be done for every item in database
    //  Will need a loop to make all full display objects

    
    @FXML
    void displayItemInformation() {
        DatabaseUtility bd = new DatabaseUtility();
        bd.setTable("itemlist");
        Set<ItemClass> tree = new TreeSet<ItemClass>(bd.createDataStructureItemClass());
        Iterator<ItemClass> it = tree.iterator();
        ItemClass temp = new ItemClass();

        while(it.hasNext()) {
            temp = it.next();
            ItemNameDisplay.setText(temp.getItemName());
            itemPriceDisplay.setText(temp.getPrice().toString());
            
        }
        

    }

}
