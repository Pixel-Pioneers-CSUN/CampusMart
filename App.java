import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {
       
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
     
    }
 
 public static void main(String[] args) {
        

        launch(args);
    }
} 

