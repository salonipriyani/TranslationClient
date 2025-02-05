package ds.edu.translationclient;

//Name: Saloni Priyani
// Andrew ID: spriyani

/*
This is a custom exception class named InvalidInputException that extends the base Exception class.
The constructor of this class takes in a String parameter named message which is the message that will be displayed
when this exception is thrown.
*/

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}