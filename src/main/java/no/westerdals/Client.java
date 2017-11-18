package no.westerdals;

import java.io.IOException;

/**
 * Class that calls on DBHandler then ask for user's input on what to call from DB
 *
 * References for structure on connection taken from
 * https://www.youtube.com/watch?v=BOUMR85B-V0 series
 *
 * @author Thien Cong Pham
 */

public class Client {
    public static void main(String[] args) {
        DBHandler program = new DBHandler();

        try {
            program.getConnection();
            System.out.println("Database Connection Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Drops table if it exist - for testing purposes like this assignment where we re-populate the tables
        program.dropTable();

        // Create table
        program.createTable();

        // Runs through method for reading from CSV File
        try {
            program.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Setup of DB and populating successful");

        // Ask for user input 
        program.userInput();
    }
}
