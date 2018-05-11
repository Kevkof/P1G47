package ui;

import domain.DomainController;
import exceptions.ExistingUsernameException;
import exceptions.PasswordException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author Kevkof
 */
public class UseCase2 {

    private ResourceBundle bundle;
    private String name;
    private String firstname;
    private String username;
    private String password;

    /**
     * Main method for the class UseCase2
     *
     * @param args
     */
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        DomainController dc = new DomainController();
        UseCase1 uc1 = new UseCase1();
        UseCase2 uc2 = new UseCase2();

        uc1.setLanguage(input);
        uc2.setBundle(uc1);

    }//End main

    /**
     * Sets the Recourcebundle
     *
     * @param uc1
     */
    public void setBundle(UseCase1 uc1) {
        bundle = uc1.getBundle();
    }

    /**
     * Gets the user input for the method SignUp and passes it to the DomainController
     *
     * @param input
     * @param dc
     * @return
     * @throws exceptions.PasswordException
     */
    public boolean signUp(Scanner input, DomainController dc) throws PasswordException {
        boolean succes = false;

        do {
            System.out.printf(bundle.getString("ui.name") + " ");
            name = input.next();
            System.out.printf(bundle.getString("ui.firstname") + " ");
            firstname = input.next();
            System.out.printf(bundle.getString("ui.userName") + " ");
            username = input.next();
            System.out.printf(bundle.getString("ui.password") + " ");
            password = input.next();

        } while (name.equals("") || firstname.equals("") || username.equals("") || password.equals("") && dc.checkPassword(password) == false);

        if (dc.existingUsername(username)) {

            //System.err.printf(bundle.getString("gui.usernamechosen"));

        } else {
            try {
                dc.signUp(name, firstname, username, password);
            } catch (ExistingUsernameException | PasswordException e) {
                System.out.println(e.getMessage());

            }
            succes = true;

        }
        return succes;
    }

    /**
     * Gets the user input for the method SignUp and passes it to the DomainController
     *
     * @param lastName
     * @param firstName
     * @param userName
     * @param Password
     * @throws exceptions.PasswordException
     */
    public void signUpString(String lastName, String firstName, String userName, String Password) throws PasswordException {
        DomainController dc = new DomainController();
        String lastnamesign = lastName;
        String firstnamesign = firstName;
        String usernamesign = userName;
        String passwordsign = Password;

        if (lastnamesign.equals("") || firstnamesign.equals("") || usernamesign.equals("") || passwordsign.equals("") && dc.checkPassword(passwordsign) == false) {
            System.err.printf(bundle.getString("ui.notFilled"));
        } else if (dc.existingUsername(username)) {
            System.err.printf(bundle.getString("gui.usernamechosen"));
        } else {
            try {
                dc.signUp(lastnamesign, firstnamesign, usernamesign, passwordsign);
            } catch (ExistingUsernameException | PasswordException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
