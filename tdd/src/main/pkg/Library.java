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

    public String searchBook(Book b) {
        for(Book i: booksInLibrary) {
            if(i.getName().equals(b.getName())){
                if(b.getAvailable() && open) {
                return "Book " + i.getName() + " is available.";
                }
            }
        }
        return "Book not found";
    }

    public Book searchBookByTitle(String title) throws SearchException {
        Book foundBook = null;
        for(Book book : booksInLibrary) {
            if(!book.getName().equalsIgnoreCase(title)) continue;
            return book;
        }

        throw new SearchException("Book not found!");
    }
}