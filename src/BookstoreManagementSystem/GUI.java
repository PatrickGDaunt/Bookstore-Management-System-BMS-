/*
 * Name of program:Bookstore Management System (BMS)
 * @author Patrick D
 * Date first created:2020-07-09
 * Date last modified: 2020-08-13
 */

package BookstoreManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.*;

import static BookstoreManagementSystem.Lab01.*;


public class GUI extends JFrame implements ActionListener{


    // Declare class variables
    String requestISBN;

    // Set constants for root frame dimensions
    private final int frame_width = 1000;
    private final int frame_height = 300;

    // Create main frame
    JFrame mainFrame = new JFrame("Giovanni's Bookstore");

    // Create panel objects
    JPanel topPanel = new JPanel();
    JPanel middlePanel =  new JPanel();
    JPanel bottomPanel = new JPanel();

    // Create buttons
    JButton addBtn = new JButton("Add");
    JButton editBtn = new JButton("Edit");
    JButton deleteBtn = new JButton("Delete");

    // Declare JLabels
    JLabel booksLabel = new JLabel("Books in the system");
    JLabel listLabel = new JLabel("List of current books goes here");
    JLabel rowLabel = new JLabel("Display of selected list index goes here");

    JTextArea textArea = new JTextArea();

    // Create table and table attributes

    JLabel static_data_label = new JLabel("Books in the system");

    JScrollPane jScrollPane = new JScrollPane();

    String column[] = {"Publisher", "Title", "Author", "Price", "ISBN", "Location/Generator Location", "Book Quantity/Audio Book Code"};

    public GUI() throws IOException {

        String inputFileName;
        File inputFile;
        String firstRow;
        Vector<Vector<String>> vectorVectorStringsData = new Vector<Vector<String>>();
        Vector<String> vectorStrings = new Vector<String>();
        Vector<String> vectorColumnIdentifiers = new Vector<String>();
        String[] columnIdentifiers = {"Publisher", "Title", "Author", "Price", "ISBN", "Location/Generator Location", "Book Quantity/Audio Book Code"};
        DefaultTableModel model = new DefaultTableModel();
        JTable jTable;

        inputFileName = "BMS_file.csv";
        inputFile = new File(inputFileName);
        try (FileReader fr = new FileReader(inputFile);
             BufferedReader br = new BufferedReader(fr))
        {
            firstRow = br.readLine().trim();
            if (firstRow != null) {
                // headers:
                //columnIdentifiers = firstRow.split(",");

                vectorColumnIdentifiers = new Vector<String>();
                for (int j =0; j < columnIdentifiers.length; j++) {
                    vectorColumnIdentifiers.add(columnIdentifiers[j]);
                }
            }
            // rows
            Object[] tableLines = br.lines().toArray();
            // data rows
            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                vectorStrings = new Vector<String>();
                for (int j =0; j < dataRow.length; j++) {
                    vectorStrings.add(dataRow[j]);
                }
                vectorVectorStringsData.add(vectorStrings);
            }
            fr.close();
        }
        catch (IOException ioe) {
            System.out.println("error: " + ioe.getMessage());
        }

        model.setDataVector(vectorVectorStringsData, vectorColumnIdentifiers);
        jTable = new JTable(model); // Create new table

        JScrollPane jsp = new JScrollPane(); // Create scroll object
        jsp.getViewport().add(jTable); // Add jTable to JScrollPane via viewport()


        // MainFrame attributes
        mainFrame.setSize(frame_width, frame_height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        // Set layout
        mainFrame.setLayout(new GridLayout(2, 0, 5, 10));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        // middlePanel.setLayout(new GridLayout(1,1,5,10));
        bottomPanel.setLayout(new GridLayout(1, 3, 5, 10));

        //setResizable(false);
        mainFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Add panels to mainFrame
        mainFrame.add(topPanel);
        //mainFrame.add(middlePanel);
        mainFrame.add(bottomPanel);

        // Add components to panels
        topPanel.add(static_data_label);
        topPanel.add(jsp);

        // middlePanel.add(textArea);
        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn)
            addFunction();
        else if (e.getSource() == editBtn)
            editRecord();
        else if (e.getSource() == deleteBtn)
            // Ask user for ISBN they wish to edit
            requestISBN = JOptionPane.showInputDialog("Enter the ISBN of the book you wish to edit");
            deleteRecordFunction1(requestISBN);
    }
}

