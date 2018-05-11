package ui;

import domain.DomainController;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;

/**
 *
 * @author Kevkof
 */
public class UseCase3 {

    private ResourceBundle bundle;

    /**
     * Main method for the class UseCase3
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DomainController dc = new DomainController();
        UseCase1 uc1 = new UseCase1();
        UseCase2 uc2 = new UseCase2();
        UseCase3 uc3 = new UseCase3();

        uc1.setLanguage(input);
        uc2.setBundle(uc1);
        uc3.setBundle(uc1);

    }//einde main

    /**
     * Sets the RecourceBundle
     *
     * @param uc1
     */
    public void setBundle(UseCase1 uc1) {
        bundle = uc1.getBundle();
    }

    public void chooseGame(Scanner input, DomainController dc) {
        String choice = null;
        boolean goodChoice = false;

        do {

            System.out.printf(bundle.getString("ui.gamechoice"));
            System.out.println(Arrays.toString(dc.getGameNames().toArray()));
            choice = input.next().toUpperCase();
            if (dc.getGameNames().contains(choice) || "STOP".equalsIgnoreCase(choice)) {
                goodChoice = true;
            }
        } while (!goodChoice);
        if ("STOP".equals(choice)) {
            dc.setGame();
        } else {
            try {
                dc.setGameDB(choice);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Selects the gameboard, reads it into the fields[][]
     *
     * @param input
     * @param dc
     */
    public void chooseGameBoard(Scanner input, DomainController dc) {
        String choice = null;
        boolean goodChoice = false;

        do {
            System.out.printf(bundle.getString("ui.choice"));
            System.out.println(Arrays.toString(dc.getGameBoardNumbers().toArray()));
            choice = input.next();
            if (dc.getGameBoardNumbers().contains(choice) || "stop".equalsIgnoreCase(choice)) {
                goodChoice = true;
            }
        } while (!goodChoice);
        if ("stop".equalsIgnoreCase(choice)) {
            dc.readGameBoardStop();
        } else {
            int boardNumber = parseInt(choice);
            try {
                dc.readGameBoard(boardNumber, dc.getGameNumber());
                System.out.printf(dc.gameToString());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }

    }

    /**
     * This is used to play the game
     *
     * @param input
     * @param dc
     */
    public void play(Scanner input, DomainController dc) {
        String move;
        String next;
        boolean completed = false;
        do {
            System.out.printf(bundle.getString("ui.move"));
            move = input.next();
            if (move.equalsIgnoreCase("stop")) {
                String output = "";
                output += bundle.getString("ui.quit");
                output += dc.getNumberOfMoves();
                output += " ";
                output += bundle.getString("ui.movesTxt");
                System.out.printf(output);
                Platform.exit();
                break;
            }
            if (move.equalsIgnoreCase("8") || move.equalsIgnoreCase("4") || move.equalsIgnoreCase("6") || move.equalsIgnoreCase("2")) {
                int direction = parseInt(move);
                dc.move(direction);
            }

            if (dc.checkCompletion()) {
                String output = dc.gameToString();
                output += bundle.getString("ui.completed");
                output += bundle.getString("ui.moves");
                output += dc.getNumberOfMoves();
                completed = true;
                System.out.printf(output);
            } else {
                System.out.printf(dc.gameToString());
            }
        } while (!completed);
        if (completed) {
            do {
                System.out.printf(bundle.getString("ui.next"));
                next = input.next();
            } while (!(next.equalsIgnoreCase("y") || (next.equalsIgnoreCase("n"))));
            switch (next) {
                case "y":
                    dc = new DomainController();
                    chooseGame(input, dc);
                    chooseGameBoard(input, dc);
                    play(input, dc);
                    break;
                case "n":
                    System.out.printf(bundle.getString("ui.exit"));
                    Platform.exit();
                    break;
                default:
                    System.out.printf(bundle.getString("ui.exit"));
                    Platform.exit();
                    break;
            }
        }
    }
}
