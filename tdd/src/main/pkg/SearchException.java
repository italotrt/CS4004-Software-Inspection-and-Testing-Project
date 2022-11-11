package pkg;

public class BookNotFound extends Exception{
    public BookNotFound(String errorMessage) {
        super(errorMessage);
    }
}
