import java.util.ArrayList;

public class Library {
    private boolean open;
    private ArrayList<Book> booksInLibrary = new ArrayList<Book>();

    public Library(boolean open) {
        this.open = open;
    }

    public void addBook(Book b) {
        booksInLibrary.add(b);
    }

    public ArrayList<Book> getBooksInLibrary() {
        return booksInLibrary;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String searchBook(Book b) {
        for(Book i: booksInLibrary) {
            if(b.getAvailable() && open && booksInLibrary.contains(b)) {
                return "Book " + i.getName() + " is available.";
            }
        }
        return "Book not found";
    }
}
