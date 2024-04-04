import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
 
public class App extends Application {
       
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("checkout.fxml"));
            
            Scene scene = new Scene(root, 1300, 800);
            primaryStage.setResizable(false);
            scene.getStylesheets().add(getClass().getResource("checkout-style.css").toExternalForm());
            
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


