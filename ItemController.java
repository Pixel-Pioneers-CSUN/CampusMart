import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {

    @FXML
    private ImageView itemImage;

    @FXML
    private Label nameLable;

    @FXML
    private Label priceLable;

    private ItemClass item;
    private MyListener myListener;

    @FXML
    void click(MouseEvent event) {
        myListener.onClickListener(item);
    }

    public void setData(ItemClass item, MyListener myListener) {

        this.item = item;
        this.myListener = myListener;
        nameLable.setText(item.getItemName());
        priceLable.setText(item.getPrice().toString());
        // change later but put a defauly photo rn
        Image image = new Image(getClass().getResourceAsStream("projectPhotos/" + item.getItemPicture()));
        // System.out.println("Passt setting imagefile");
        itemImage.setImage(image);
        System.out.println("out of image set");
    }



}
