package BookstoreManagementSystem;

/**
 *
 * Name of program:Bookstore Management System (BMS) 
 * @author Patrick D
 * Date first created:2020-07-09
 * Date last modified: 2020-07-24
 *
 */

// Imports

// Create exception class for Price
public class UnacceptablePrice extends Exception{

    // Subclass constructor with null as its detail message
    public UnacceptablePrice() {
        super("Please enter a price greater than $0.0");
    }
    
    // Subclass constructor with a specified detail message
    public UnacceptablePrice(String message) {
        super(message);
    }
}
