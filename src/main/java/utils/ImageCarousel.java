package utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.InputStream;


public class ImageCarousel extends HBox {
    private ImageView[] imageViews;
    private int currentIndex = 0;

    // constructor for the ImageCarousel class to set num items to show
    // doing it this way because direct image paths throwing null exceptions for some reason
    public ImageCarousel(int numItemsToShow) {
        // load the images
        Image[] images = {
                loadImage("Burrito.png"),
                loadImage("Salad.png"),
                loadImage("Taquitos.png"),
                loadImage("Vegetable Snack Pack.png"),
                loadImage("Dr. Pepper.png")
        };

        // create ImageViews for each image
        imageViews = new ImageView[images.length];
        // traverse the array of images
        for (int i = 0; i < images.length; i++) {
            imageViews[i] = new ImageView(images[i]);
            imageViews[i].setPreserveRatio(true);
            imageViews[i].setFitHeight(200); // height of each image
        }

        // adding the images to the ImageCarousel
        getChildren().addAll(imageViews);

        // animating the carousel to switch every few seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            currentIndex = (currentIndex + 1) % imageViews.length;
            showImages(currentIndex, numItemsToShow);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // show initial images
        showImages(currentIndex, numItemsToShow);
    }

    private Image loadImage(String fileName) {
        InputStream inputStream = getClass().getResourceAsStream("/images/" + fileName);
        if (inputStream != null) {
            return new Image(inputStream);
        } else {
            throw new IllegalArgumentException("Image file not found: " + fileName);
        }
    }

    private void showImages(int startingIndex, int numItemsToShow) {
        // Calculate the distance to slide for each image
        double slideDistance = getWidth() / numItemsToShow;

        // Calculate the initial position for the first image
        double initialX = -(startingIndex * slideDistance);

        // Slide each image to its position
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imageView = imageViews[i];
            int index = (startingIndex + i) % imageViews.length;
            double targetX = initialX + (index * slideDistance);
            slideImage(imageView, targetX);
        }
    }

    private void slideImage(ImageView imageView, double targetX) {
        // Calculate the current X position of the image
        double currentX = imageView.getTranslateX();

        // Calculate the distance to slide based on the difference between current and target positions
        double distance = targetX - currentX;

        // Create a TranslateTransition for the given image view
        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), imageView);
        transition.setByX(distance); // Use setByX to specify relative movement
        transition.play();
    }
}