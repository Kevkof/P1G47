package ui;

import domain.DomainController;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author Kevkof
 */
public class UseCase1 {

    private ResourceBundle bundle;

    /**
     * Main method for the class UseCase1
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UseCase1 uc1 = new UseCase1();
        DomainController dc = new DomainController();
        uc1.setLanguage(input);
//        uc1.signIn(input, dc);
    }

    /**
     * Sets the language
     *
     * @param input
     */
    public void setLanguage(Scanner input) {
        boolean correctLanguage = false;
        String language = "";
        do {
            System.out.printf("Choose your language (en) | Kies uw taal (nl) | Choisissez votre langue (fr): ");
            language = input.next();
            if (language.equalsIgnoreCase("en") || language.equalsIgnoreCase("nl") || language.equalsIgnoreCase("fr")) {
                correctLanguage = true;
            }
        } while (!correctLanguage);
        Locale locale = new Locale(language);
        bundle = ResourceBundle.getBundle("properties/game", locale);
    }

    /**
     * Returns the correct ResourceBundle
     *
     * @return ResourceBundle
     */
    public ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * Gets the user input for the method signIn and passes it to the DomainController
     *
     * @param input
     * @param dc
     * @return String
     */
    public String signIn(Scanner input, DomainController dc) {
        String userName = null;
        String password = null;
        String answer = null;

        do {
            System.out.printf(bundle.getString("ui.singInAfterUp"));
            System.out.printf(bundle.getString("ui.userName"));
            userName = input.next();
            if (userName.equalsIgnoreCase("stop")) {
                break;
            }
            System.out.printf(bundle.getString("ui.password"));
            password = input.next();
        } while (dc.checkUsernameAndPassword(userName, password) == false);
        if (userName.equalsIgnoreCase("stop")) {
            answer = "stop";
        } else if (dc.checkUsernameAndPassword(userName, password)) {
            dc.signIn(userName, password);
            if (dc.isAdmin(userName, password) == true) {
                do {
                    System.out.printf(bundle.getString("ui.play") + "\n" + bundle.getString("ui.configure") + "\n" + bundle.getString("ui.change") + "\n" + bundle.getString("ui.stop") + "\n");
                    answer = input.next();
                } while (!"1".equals(answer) && !"2".equals(answer) && !"3".equals(answer) && !"stop".equalsIgnoreCase(answer));
            } else {
                do {
                    System.out.printf(bundle.getString("ui.play") + "\n" + bundle.getString("ui.stop") + "\n");
                    answer = input.next();
                } while (!"1".equals(answer) && !"stop".equalsIgnoreCase(answer));

            }

        }
        return answer;

    }

    /**
     * Gets the user input for the method signIn and passes it to the DomainController
     *
     * @param username
     * @param password
     * @return
     */
    public String signInString(String username, String password) {
        DomainController dc = new DomainController();
        Scanner input = new Scanner(System.in);
        while (dc.checkUsernameAndPassword(username, password) == false) {
            System.err.printf(bundle.getString("ui.try"));
        }
        dc.signIn(username, password);

        if (dc.isAdmin(username, password) == true) {
            System.out.printf(bundle.getString("ui.play") + "\n" + bundle.getString("ui.configure") + "\n" + bundle.getString("ui.change") + "\n");
            String choice = input.next();
            return choice;
        } else {
            String one;
            do {
                System.out.printf(bundle.getString("ui.play"));
                one = input.next();
            } while (!"1".equals(one));
            return one;
        }
    }

    public void setBundle(String language) {
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
