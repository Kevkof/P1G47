package gui;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Kevkof
 */
public class LanguageSelectionScreen extends GridPane {
    Stage secondStage;
    ResourceBundle bundle;
    
    public LanguageSelectionScreen(Stage primairyStage) {
        secondStage = primairyStage;
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        Label lblTitle = new Label("Sokoban");
        lblTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(lblTitle, 0, 0, 2, 1);
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("English");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        this.add(rb1, 0, 1);
        RadioButton rb2 = new RadioButton("Nederlands");
        rb2.setToggleGroup(group);
        this.add(rb2, 1, 1);
        RadioButton rb3 = new RadioButton("Français");
        rb3.setToggleGroup(group);
        this.add(rb3, 2, 1);
        Button btn = new Button("OK");
        this.add(btn, 1, 2);

        btn.setOnAction((ActionEvent event) -> {
            if (rb1.isSelected()) {
                SignUP sign = new SignUP("en");
                Scene scene1 = new Scene(sign, 700, 400);
                setBundle("en");
                secondStage.setScene(scene1);
                secondStage.setTitle("Sokoban");
                secondStage.show();
                secondStage.setOnCloseRequest((WindowEvent event1) -> {
                    System.out.printf(bundle.getString("gui.close"));
                    Platform.exit();
                });
            } else if (rb2.isSelected()) {
                SignUP sign = new SignUP("nl");
                Scene scene2 = new Scene(sign, 700, 400);
                setBundle("nl");
                secondStage.setScene(scene2);
                secondStage.setTitle("Sokoban");
                secondStage.show();
                secondStage.setOnCloseRequest((WindowEvent event1) -> {
                    System.out.printf(bundle.getString("gui.close"));
                    Platform.exit();
                });
            } else if (rb3.isSelected()) {
                SignUP sign = new SignUP("fr");
                Scene scene3 = new Scene(sign, 700, 400);
                setBundle("fr");
                secondStage.setScene(scene3);
                secondStage.setTitle("Sokoban");
                secondStage.show();
                secondStage.setOnCloseRequest((WindowEvent event1) -> {
                    System.out.printf(bundle.getString("gui.close"));
                    Platform.exit();
                });
            }
        });

    }
    
    private void setBundle(String language){
       boolean correctLanguage = false;
        do {
            if (language.equalsIgnoreCase("en") || language.equalsIgnoreCase("nl") || language.equalsIgnoreCase("fr")) {
                correctLanguage = true;
            }
        } while (!correctLanguage);
        Locale locale = new Locale(language);
        bundle = ResourceBundle.getBundle("properties/game", locale);
    }

}
