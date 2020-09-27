/*
 * Name of program:Bookstore Management System (BMS)
 * @author Patrick D
 * Date first created:2020-07-09
 * Date last modified: 2020-08-13
 */

package BookstoreManagementSystem;

// Imports
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Lab01 {


    /**
     * Function that asks user for ISBN and allows user to enter new values for that record
     */
    public static void editRecord(){

        // Scanner object to read file
        Scanner x;

        // File handling
        String filepath = "BMS_file.csv";
        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        // Declare function variables
        String publisherString;
        String authorString;
        String titleString;
        String priceDouble;
        String ISBNString;
        String locationString;
        String numCopiesInt;
        String commaDelimiter = ",";

        try{
            // Set tempFile object into PrintWriter
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            // Send filepath through scanner
            x = new Scanner(new File(filepath));

            // Delimit scanner object
            x.useDelimiter("[,\n]");

            // Ask user for ISBN they wish to edit
            String requestISBN = JOptionPane.showInputDialog("Enter the ISBN of the book you wish to edit");

            // While loop to iterate through scanner object
            while (x.hasNext()){

                // Set next value to respective variables
                publisherString = x.next();
                titleString = x.next();
                authorString = x.next();
                priceDouble = x.next();
                ISBNString = x.next();
                locationString = x.next();
                numCopiesInt = x.next();

                // If statement. If ISBNString is equal to the requestISBN execute the block
                if(ISBNString.equals(requestISBN)){
                    String newPublisherString = JOptionPane.showInputDialog("Enter new publisher");
                    String newTitleString = JOptionPane.showInputDialog("Enter new title");
                    String newAuthorString = JOptionPane.showInputDialog("Enter new Author");
                    String newPriceDouble = JOptionPane.showInputDialog("Enter new price");
                    String newISBNString = ISBNString;
                    String newLocationString = JOptionPane.showInputDialog("Enter new printed book location or audiobook generator code location");
                    String newNumCopiesInt = JOptionPane.showInputDialog("Enter number of copies for printed book or generator code for audiobook");
                    pw.printf(newPublisherString + commaDelimiter + newTitleString + commaDelimiter + newAuthorString +
                            commaDelimiter + newPriceDouble + commaDelimiter + newISBNString + commaDelimiter +
                            newLocationString + commaDelimiter + newNumCopiesInt + "\n");
                } else{
                    pw.printf(publisherString + commaDelimiter + titleString + commaDelimiter + authorString +
                            commaDelimiter + priceDouble + commaDelimiter + ISBNString + commaDelimiter +
                            locationString + commaDelimiter + numCopiesInt + "\n");
                }

            } x.close(); pw.flush(); pw.close();

            // Delete oldFile, create new file object to original file path, rename newFile to original file name
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error, please try again!");
        }
    }




    /**
     * Function to add a record to a csv file
     */
    public static void addFunction() {
        // Declare variables
        int continueSearch;
        String cont;
        int optionBook;

        // Create empty ArrayLists to store constructor parameters from user inputs
        ArrayList<Object> printedbookArrayList = new ArrayList<>();
        ArrayList<Object> audiobookArrayList = new ArrayList<>();

        do {
            optionBook = Integer.parseInt(JOptionPane.showInputDialog("Please select "
                    + "type of book:\n\t 1 - Printed Book\n\t 2 - Audiobook"
                    + "\n\t Enter any other number to exit"));

            if (optionBook == 1){
                // Request user input for BookStoreManagementSystem.PrintedBook
                // Request input for publisher
                String publisherString = JOptionPane.showInputDialog("Please enter book publisher");

                // Request input fot title
                String titleString = JOptionPane.showInputDialog("Please enter book title");

                // Request input for author
                String authorString = JOptionPane.showInputDialog("Please enter book author");

                // Request input for price with BookStoreManagementSystem.UnacceptablePrice exception
                double priceDouble = 1.0;
                try {
                    priceDouble = Double.parseDouble
                            (JOptionPane.showInputDialog("Please enter book price"));
                    if (priceDouble <= 0) {
                        throw (new UnacceptablePrice());
                    }
                } catch (UnacceptablePrice pe) {
                    JOptionPane.showMessageDialog(null, pe.getMessage());
                    System.exit(1);
                }

                // Request input for ISBN
                String ISBNString = JOptionPane.showInputDialog("Please enter book ISBN");

                // Request input for location
                String locationString = JOptionPane.showInputDialog("Please enter book location");

                // Request number of copies
                int numCopiesInt = Integer.parseInt(JOptionPane.showInputDialog("Please enter book quantity"));

                // Add new BookStoreManagementSystem.PrintedBook object to printedbookArrayList with variables as arguments
                printedbookArrayList.add(new PrintedBook(locationString, numCopiesInt,
                        publisherString, titleString, authorString,
                        priceDouble, ISBNString));

                // Write printedbookArrayList to filename object
                try {
                    String commaDelimiter = ",";
                    String newLineSeparator = "\n";

                    // File handling
                    File filename =
                            new File("BMS_file.csv");

                    FileWriter fw = new FileWriter(filename, true);
                    for(Object ignored : printedbookArrayList) {
                        //fw.write(newLineSeparator);
                        fw.append(publisherString);
                        fw.append(commaDelimiter);
                        fw.append(titleString);
                        fw.append(commaDelimiter);
                        fw.append(authorString);
                        fw.append(commaDelimiter);
                        fw.append(String.valueOf(priceDouble));
                        fw.append(commaDelimiter);
                        fw.append(ISBNString);
                        fw.append(commaDelimiter);
                        fw.append(locationString);
                        fw.append(commaDelimiter);
                        fw.append(String.valueOf(numCopiesInt));

                    }
                    fw.flush();
                    fw.close();
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null,"The file was not found!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Display the toString() of the last element in the printedbookArrayList
                JOptionPane.showMessageDialog(null,
                        printedbookArrayList.get(printedbookArrayList.size()-1).toString());

            } else if (optionBook == 2) {
                // Request user input for BookStoreManagementSystem.PrintedBook
                // Request input for publisher
                String publisherString = JOptionPane.showInputDialog("Please enter book publisher");

                // Request input fot title
                String titleString = JOptionPane.showInputDialog("Please enter book title");

                // Request input for author
                String authorString = JOptionPane.showInputDialog("Please enter book author");

                // Request input for price with BookStoreManagementSystem.UnacceptablePrice exception
                double priceDouble = 1.0;
                try {
                    priceDouble = Double.parseDouble
                            (JOptionPane.showInputDialog("Please enter book price"));
                    if (priceDouble <= 0) {
                        throw (new UnacceptablePrice("Invalid Entry!\n\n " +
                                titleString + " audiobook is not free!"));
                    }
                } catch (UnacceptablePrice pe) {
                    JOptionPane.showMessageDialog(null, pe.getMessage());
                    System.exit(1);
                }

                // Request input for ISBN
                String ISBNString = JOptionPane.showInputDialog("Please enter book ISBN");

                // Request generated code location for BookStoreManagementSystem.AudioBook object
                String locationString = JOptionPane.showInputDialog("Please "
                        + "enter audiobook code generator location");

                // Request generated code for BookStoreManagementSystem.AudioBook object
                int numCopiesInt = Integer.parseInt(JOptionPane.showInputDialog("Please enter the generated code for"
                        + " the audiobook"));

                // Add new BookStoreManagementSystem.AudioBook object to audiobookArrayList ArrayList with variables as arguments
                audiobookArrayList.add(new AudioBook(publisherString,
                        titleString, authorString, priceDouble, ISBNString,
                        locationString, numCopiesInt));

                // Write audiobookArrayList to filename object
                try {
                    String commaDelimiter = ",";
                    String newLineSeparator = "\n";

                    // File handling
                    File filename =
                            new File("BMS_file.csv");

                    FileWriter fw = new FileWriter(filename, true);

                    for(Object ignored : printedbookArrayList) {
                        //fw.write(newLineSeparator);
                        fw.append(publisherString);
                        fw.append(commaDelimiter);
                        fw.append(titleString);
                        fw.append(commaDelimiter);
                        fw.append(authorString);
                        fw.append(commaDelimiter);
                        fw.append(String.valueOf(priceDouble));
                        fw.append(commaDelimiter);
                        fw.append(ISBNString);
                        fw.append(commaDelimiter);
                        fw.append(locationString);
                        fw.append(commaDelimiter);
                        fw.append(String.valueOf(numCopiesInt));
                    }

                    fw.flush();
                    fw.close();
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null,"The file was not found!");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Display the toString() of the last element in the audiobookArrayList ArrayList
                JOptionPane.showMessageDialog(null, audiobookArrayList.get(audiobookArrayList.size()-1).toString());

            } else {
                break;
            }// Request to continue of exit
            cont = JOptionPane.showInputDialog("Type 1 to Continue or 2 to Exit");
            continueSearch = Integer.parseInt(cont);

        } while (continueSearch == 1);
    }

    /**
     * Delete ISBN (from user input) from csv file
     */
    public static void deleteRecordFunction1(String requestISBN) {


        // Scanner object to read file
        Scanner x;

        // File handling
        String filepath = "BMS_file.csv";
        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        // Declare function variables
        String publisherString;
        String titleString;
        String authorString;
        String priceDouble;
        String ISBNString;
        String locationString;
        String numCopiesInt;
        String commaDelimiter = ",";

        try{
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()){
                publisherString = x.next();
                titleString = x.next();
                authorString = x.next();
                priceDouble = x.next();
                ISBNString = x.next();
                locationString = x.next();
                numCopiesInt = x.next();

                if(!ISBNString.equals(requestISBN)){
                    pw.printf(publisherString + commaDelimiter + titleString + commaDelimiter + authorString +
                            commaDelimiter + priceDouble + commaDelimiter + ISBNString + commaDelimiter +
                            locationString + commaDelimiter + numCopiesInt + "\n");
                }
            } x.close(); pw.flush(); pw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Delete Error, please try again!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
