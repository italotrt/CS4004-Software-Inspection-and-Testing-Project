package pkg;

import java.util.ArrayList;

public class Department {
    private String name;
    private double budget;
    private ArrayList<Book> departmentBooks =  new ArrayList<>();
    private ArrayList<Book> currentRentedBooks = new ArrayList<>();
    private ArrayList<String> currentSubscriptions = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public ArrayList<Book> getCurrentRentedBooks() {
        return currentRentedBooks;
    }

    public ArrayList<Book> getDepartmentBooks() {
        return departmentBooks;
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

    public ArrayList<String> getCurrentSubscriptions() {
        return currentSubscriptions;
    }

    public void subscribe(Journal j) {
        currentSubscriptions.add(j.getName());
    }

    public String getName() {
        return name;
    }

    public void purchaseBooks(Book b, double total) {
        double totalSpend = b.getPrice() * total;
        if(budget >= totalSpend) {
            departmentBooks.add(b);
        }
    }

    public double getBudget() {
        return budget;
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