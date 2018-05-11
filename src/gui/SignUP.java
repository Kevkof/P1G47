package gui;

import domain.DomainController;
import exceptions.PasswordException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.UseCase1;
import ui.UseCase2;
import ui.UseCase3;

/**
 *
 * @author Kevkof
 */
public class SignUP extends GridPane {

    private ResourceBundle bundle;
    private UseCase1 uc1 = new UseCase1();
    private UseCase2 uc2 = new UseCase2();
    private UseCase3 uc3 = new UseCase3();
    private Scanner input = new Scanner(System.in);
    private DomainController dc = new DomainController();

    public SignUP(String language) {
        uc1.setBundle(language);
        uc2.setBundle(uc1);
        uc3.setBundle(uc1);
        setBundle(uc1);

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        Label lblTitle = new Label(bundle.getString("ui.signUp"));
        lblTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Label lblLastName = new Label(bundle.getString("ui.name"));
        TextField txfLastName = new TextField();
        txfLastName.setStyle("-fx-border-color:black;");

        Label lblFirstName = new Label(bundle.getString("ui.firstname"));
        TextField txfFirstName = new TextField();
        txfFirstName.setStyle("-fx-border-color:black;");

        Label lblUserName = new Label(bundle.getString("ui.userName"));
        lblUserName.setTooltip(new Tooltip(bundle.getString("gui.length")));
        TextField txfUserName = new TextField();
        txfUserName.setTooltip(new Tooltip(bundle.getString("gui.length")));
        txfUserName.setStyle("-fx-border-color:black;");
        Label errUsername = new Label("");
        errUsername.setTextFill(Color.web("#FF0000"));

        Label lblPassword = new Label(bundle.getString("ui.password"));
        lblPassword.setTooltip(new Tooltip(bundle.getString("gui.password")));
        PasswordField psf = new PasswordField();
        psf.setTooltip(new Tooltip(bundle.getString("gui.password")));
        psf.setStyle("-fx-border-color:black;");
        Label errPW = new Label("");
        errPW.setTextFill(Color.web("#FF0000"));

        CheckBox cb = new CheckBox();
        cb.setSelected(true);
        cb.setAlignment(Pos.CENTER_RIGHT);
        Label lblCB = new Label(bundle.getString("gui.CB"));
        Button btn = new Button(bundle.getString("ui.signUp"));

        Label lblInfo = new Label(bundle.getString("gui.button"));
        lblInfo.setTextFill(Color.web("#00b7ff"));

        this.add(lblTitle, 0, 0, 2, 1);
        this.add(lblLastName, 0, 1);
        this.add(txfLastName, 1, 1);
        this.add(lblFirstName, 0, 2);
        this.add(txfFirstName, 1, 2);
        this.add(lblUserName, 0, 3);
        this.add(txfUserName, 1, 3);
        this.add(errUsername, 2, 3);
        this.add(lblPassword, 0, 4);
        this.add(psf, 1, 4);
        this.add(errPW, 2, 4);
        this.add(cb, 0, 5);
        this.add(lblCB, 1, 5);
        this.add(btn, 2, 5);
        this.add(lblInfo, 0, 6, 3, 1);

        cb.setOnAction((ActionEvent event) -> {
            if (cb.isSelected()) {
                lblTitle.setText(bundle.getString("ui.signUp"));
                btn.setText(bundle.getString("ui.signUp"));
                txfLastName.setVisible(true);
                txfFirstName.setVisible(true);
                txfLastName.clear();
                txfFirstName.clear();
                lblLastName.setVisible(true);
                lblFirstName.setVisible(true);
                errPW.setText("");
                errUsername.setText("");
                txfUserName.clear();
                psf.clear();
            } else if (!cb.isSelected()) {
                lblTitle.setText(bundle.getString("ui.signIn"));
                btn.setText(bundle.getString("ui.signIn"));
                txfLastName.setVisible(false);
                txfFirstName.setVisible(false);
                txfLastName.clear();
                txfFirstName.clear();
                lblLastName.setVisible(false);
                lblFirstName.setVisible(false);
                errPW.setText("");
                errUsername.setText("");
                txfUserName.clear();
                psf.clear();
            }
        });

        btn.setOnAction((ActionEvent event) -> {
            errPW.setText("");
            errUsername.setText("");
            if (btn.getText().equals(bundle.getString("ui.signIn"))) {
                if (!dc.checkUsernameAndPassword(txfUserName.getText(), psf.getText())) {
                    errUsername.setText(bundle.getString("gui.mismatch"));
                    errPW.setText(bundle.getString("gui.mismatch"));
                } else {
                    String UC1response = uc1.signInString(txfUserName.getText(), psf.getText());
                    switch (UC1response) {
                        case "1":
                            uc3.chooseGame(input, dc);
                            if (dc.getGameNumber() == 0) {
                                System.out.printf(bundle.getString("ui.exit"));
                                Platform.exit();
                                break;
                            } else {
                                uc3.chooseGameBoard(input, dc);
                                if (dc.getGameNumber() == 0) {
                                    System.out.printf(bundle.getString("ui.exit"));
                                    Platform.exit();
                                    break;
                                } else {
                                    uc3.play(input, dc);
                                }
                            }
                            break;
                        case "2":
                            System.out.println(bundle.getString("ui.configure"));
                            System.out.println(bundle.getString("ui.exit"));
                            Platform.exit();
                            break;
                        case "3":
                            System.out.println(bundle.getString("ui.change"));
                            System.out.println(bundle.getString("ui.exit"));
                            Platform.exit();
                            break;
                        default:
                            Platform.exit();
                            break;
                    }
                }
            } else if (btn.getText().equals(bundle.getString("ui.signUp"))) {
                if (dc.existingUsername(txfUserName.getText())) {
                    errUsername.setText(bundle.getString("gui.usernamechosen"));
                } else {
                    try {
                        if (!dc.checkPassword(psf.getText())) {
                            errPW.setText(bundle.getString("gui.PWwrong"));
                        }
                    } catch (PasswordException ex) {
                        errPW.setText(bundle.getString("gui.PWwrong"));
                    }
                }
                if (!dc.existingUsername(txfUserName.getText())) {
                    try {
                        uc2.signUpString(txfLastName.getText(), txfFirstName.getText(), txfUserName.getText(), psf.getText());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (dc.checkUsernameAndPassword(txfUserName.getText(), psf.getText())) {
                        String UC1response = uc1.signInString(txfUserName.getText(), psf.getText());
                        switch (UC1response) {
                            case "1":
                                uc3.chooseGame(input, dc);
                                if (dc.getGameNumber() == 0) {
                                    System.out.printf(bundle.getString("ui.exit"));
                                    Platform.exit();
                                } else {
                                    uc3.chooseGameBoard(input, dc);
                                    if (dc.getGameNumber() == 0) {
                                        System.out.printf(bundle.getString("ui.exit"));
                                        Platform.exit();
                                    } else {
                                        uc3.play(input, dc);
                                    }
                                }
                                break;
                            case "2":
                                System.out.println(bundle.getString("ui.configure"));
                                System.out.println(bundle.getString("ui.exit"));
                                Platform.exit();
                                break;
                            case "3":
                                System.out.println(bundle.getString("ui.change"));
                                System.out.println(bundle.getString("ui.exit"));
                                Platform.exit();
                            default:
                                Platform.exit();
                                break;
                        }
                    }
                }

            }
        });

    }

    private void setBundle(UseCase1 uc1) {
        bundle = uc1.getBundle();
    }
}
