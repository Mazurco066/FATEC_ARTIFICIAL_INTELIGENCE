package visualfamilyclassifier;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VisualFamilyClassifier extends Application {
        
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        // Definição do XML da janela
        Parent root = FXMLLoader.load(getClass().getResource("view/VisualClassifier.fxml"));
        
        // Inicializando a Scene
        Scene scene = new Scene(root);       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
