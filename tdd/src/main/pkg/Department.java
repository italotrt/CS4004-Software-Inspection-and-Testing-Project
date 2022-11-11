package pkg;

import java.util.ArrayList;

public class Department {
    private String name;
    private double budget;
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
            System.out.println("Book is already rented");
        }
    }
    public String getName(){
        return name;
    }

    public double purchaseBooks(Book b, double total){
        double totalSpend = b.getPrice()*total;
        return totalSpend;
    }

    public double getBudget() {
        return budget;
    }

    public boolean bookBudgetCheck(double budget, double spent){
        if(budget - spent >= 0){
            return true;
        } else {
            return false;
        }
    }
    public void setBudget(double i) {
        budget = i;
    }
}/*
=======
import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Book> currentRentedBooks = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public ArrayList<Book> getCurrentRentedBooks() {
        return currentRentedBooks;
    }

    public void rentBook(Book b) {
        if (b.getAvailable()) {
            b.setAvailable(false);
            currentRentedBooks.add(b);
            b.addToPreviousOwners(name);
        } else {
            System.out.println("Book is already rented");
        }
    }

    public String getName() {
        return name;
    }
}
*/