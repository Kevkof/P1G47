package gui;

import domain.DomainController;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import ui.UseCase1;
import ui.UseCase2;
import ui.UseCase3;

/**
 *
 * @author Kevkof
 */
public class StartUpConsole {

    private static ResourceBundle bundle;

    public static void main(String[] args) {
        DomainController dc = new DomainController();
        Scanner input = new Scanner(System.in);
        StartUpConsole start = new StartUpConsole();
        UseCase1 uc1 = new UseCase1();
        UseCase2 uc2 = new UseCase2();
        UseCase3 uc3 = new UseCase3();

        boolean correctChoice = false;
        boolean signUpSucces = false;

        uc1.setLanguage(input);
        start.setBundle(uc1);
        uc2.setBundle(uc1);
        uc3.setBundle(uc1);

        do {
            System.out.printf(bundle.getString("ui.signInConsole") + "\n" + bundle.getString("ui.signUpConsole") + "\n" + bundle.getString("ui.stop") + "\n");
            String choice = input.next();
            switch (choice) {
                case "1":
                    correctChoice = true;
                    String response = uc1.signIn(input, dc);
                    switch (response) {
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
                            System.out.printf(bundle.getString("ui.configure"));
                            System.out.printf(bundle.getString("ui.exit"));
                            Platform.exit();
                            break;
                        case "3":
                            System.out.printf(bundle.getString("ui.change"));
                            System.out.printf(bundle.getString("ui.exit"));
                            Platform.exit();
                            break;
                        case "stop":
                            System.out.printf(bundle.getString("ui.exit"));
                            Platform.exit();
                            break;
                        default:
                            break;
                    }
                    break;
                case "2":
                    correctChoice = true;
                     {
                        try {
                            signUpSucces = uc2.signUp(input, dc);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (!signUpSucces) {
                        System.err.printf(bundle.getString("gui.usernamechosen"));
                    } else {
                        String uc1response = uc1.signIn(input, dc);
                        switch (uc1response) {
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
                                System.out.printf(bundle.getString("ui.configure"));
                                System.out.printf(bundle.getString("ui.exit"));
                                Platform.exit();
                                break;
                            case "3":
                                System.out.printf(bundle.getString("ui.change"));
                                System.out.printf(bundle.getString("ui.exit"));
                                Platform.exit();
                                break;
                            case "stop":
                                System.out.printf(bundle.getString("ui.exit"));
                                Platform.exit();
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "stop":
                    correctChoice = true;
                    System.out.printf(bundle.getString("ui.exit"));
                    Platform.exit();
                    break;
            }
        } while (!correctChoice);
    }

    public void setBundle(UseCase1 uc1) {
        bundle = uc1.getBundle();
    }
}
