package pkg;

import java.util.ArrayList;

public class Library {
    private boolean open;
    private ArrayList<Book> booksInLibrary = new ArrayList<>();

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

    public boolean searchBook(Book b) throws SearchException {
        for (Book i : booksInLibrary) {
            if (i.getName().equals(b.getName())) {
                if (b.getAvailable()) {
                    return true;
                }
            }
        }
        throw new SearchException("no book found");
    }

    public boolean isOpen() {
        return open;
    }

    public Book searchBookByTitle(String title) throws SearchException {
        for (Book book : booksInLibrary) {
            if (book.getName().equalsIgnoreCase(title)){return book;}
        }
        throw new SearchException("Book not found!");


    }
}
