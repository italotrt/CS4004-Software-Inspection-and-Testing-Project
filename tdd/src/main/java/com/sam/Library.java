package com.sam;

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
                return "com.sam.Book " + i.getName() + " is available.";
                }
            }
        }
        return "com.sam.Book not found";
    }
}
