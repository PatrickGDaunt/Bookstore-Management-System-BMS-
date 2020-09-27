/**
 *
 * Name of program:Bookstore Management System (BMS) 
 * @author Patrick D
 * Date first created:2020-07-09
 * Date last modified: 2020-07-23
 *
 */

// Imports
package BookstoreManagementSystem;

public class AudioBook extends Book {

    // Declare subclass variables
    private String gen_location;
    private int gen_code;
    
    /**
     * Subclass constructor
     * @param book_publisher
     * @param book_title
     * @param book_author
     * @param book_price
     * @param book_ISBN
     * @param gen_location
     * @param gen_code 
     */
    public AudioBook(String book_publisher, String book_title, String book_author,
            double book_price, String book_ISBN, String gen_location, int gen_code) {
        super(book_publisher, book_title, book_author, book_price, book_ISBN);
        this.gen_location = gen_location;
        this.gen_code = gen_code; 
    }
    
    /**
     * Gets the audio book's code generator location
     * @return A String representing audio book's code generator location
     */
    public String getGen_location() {
        return gen_location;
    }
    
    /**
     * Creates a placeholder to store the location for the audio book's code generator
     * @param gen_location A String representing audio book's code generator location
     */
    public void setGen_location(String gen_location) {
        this.gen_location = gen_location;
    }
    
    /**
     * Gets the audio book's generated code
     * @return A String representing the audio book's generated code
     */
    public int getGen_code() {
        return gen_code;
    }
    
    /**
     * Creates a placeholder to store the audio book's generated code
     * @param gen_code A String representing the audio book's generated code
     */
    public void setGen_code(int gen_code) {
        this.gen_code = gen_code;
    }
    
    /**
     * Override super class abstract toString()
     * @return statement summarizing audio book details 
     */
    @Override
    public String toString() {
        return ("The " + getBook_title() + ", written by " + getBook_author() +
                ", and published by " + getBook_publisher() + ". ISBN: " +
                getBook_ISBN() + ". Price: $" + getBook_price() + "."
                + "\nCode Generator location: " + getGen_location()
                + "\n\tAudiobook code: " + getGen_code());
    }
}
