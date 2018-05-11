package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Kevkof
 */
public class StartUpGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        LanguageSelectionScreen grid = new LanguageSelectionScreen(primaryStage);
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sokoban");
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            System.out.println("We are closing this window ... therefor also this application");
            Platform.exit();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
