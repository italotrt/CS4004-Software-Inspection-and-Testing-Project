package com.sam;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Book> currentRentedBooks = new ArrayList<>();

    public Department(String name){
        this.name = name;
    }

    public ArrayList<Book> getCurrentRentedBooks(){
        return currentRentedBooks;
    }

    public void rentBook(Book b){
        if(b.getAvailable()){
            b.setAvailable(false);
            currentRentedBooks.add(b);
            b.addToPreviousOwners(name);
        }else{
            System.out.println("com.sam.Book is already rented");
        }
    }
    public String getName(){
        return name;
    }


}/*
=======
import java.util.ArrayList;

public class com.sam.Department {
    private String name;
    private ArrayList<com.sam.Book> currentRentedBooks = new ArrayList<>();

    public com.sam.Department(String name) {
        this.name = name;
    }

    public ArrayList<com.sam.Book> getCurrentRentedBooks() {
        return currentRentedBooks;
    }

    public void rentBook(com.sam.Book b) {
        if (b.getAvailable()) {
            b.setAvailable(false);
            currentRentedBooks.add(b);
            b.addToPreviousOwners(name);
        } else {
            System.out.println("com.sam.Book is already rented");
        }
    }

    public String getName() {
        return name;
    }
}
*/