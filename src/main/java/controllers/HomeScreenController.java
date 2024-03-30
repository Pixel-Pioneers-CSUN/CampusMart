package controllers;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class HomeScreenController {

    @FXML
    private StackPane centerStackPane;

    @FXML
    private ImageView centerBackgroundImageView;

    public void initialize() {

        // Set fitWidth and fitHeight to 0.0 (which results in the computed size)
//        centerBackgroundImageView.setFitWidth(0.0);
//        centerBackgroundImageView.setFitHeight(0.0);
//
//        centerBackgroundImageView.fitWidthProperty().bind(homeScreenCenterStackPane.widthProperty());
//        centerBackgroundImageView.fitHeightProperty().bind(homeScreenCenterStackPane.heightProperty());

    }
}
