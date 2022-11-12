package pkg;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Book {
    private String name;
    private String subject;
    private String department;
    private double price;
    String uniOfOrigin = "";
    private boolean available = true;

    private boolean reserved = false;
    private int lengthOfLoan;
    private ArrayList<String> previousOwners = new ArrayList<>();

    public Book(String name, String subject, String uniOfOrigin, String department) {
        this.name = name;
        this.subject = subject;
        this.uniOfOrigin = uniOfOrigin;
        this.department = department;
    }

    public Book(String name, String subject, int lengthOfLoan, String department) {
        this.name = name;
        this.subject = subject;
        this.lengthOfLoan = lengthOfLoan;
        this.department = department;
    }

    public boolean getAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<String> getPreviousOwners() {
        return previousOwners;
    }

    public void setAvailable(boolean b) {
        available = b;
    }

    public void addToPreviousOwners(String d) {
        previousOwners.add(d);

    }

    public int getLengthOfLoan() {
        return lengthOfLoan;
    }

    public void setPrice(double i) {
        price = i;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return reserved;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}


/*
=======
import java.lang.reflect.Array;
import java.util.ArrayList;
*/

/*
public class Book {
    private String name;
    private String subject;
    private String department;
    private boolean available = true;
    private ArrayList<String> previousOwners = new ArrayList<>();

    public Book(String name, String subject){
        this.name = name;
        this.subject = subject;
    }

    public void setAvailable(boolean b){
        available = b;
    }

    public boolean getAvailable(){
        return available;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<String> getPreviousOwners(){
        return previousOwners;
    }

    public void addToPreviousOwners(String name){
        previousOwners.add(name);
    }
}
>>>>>>> italo:src/main/java/Book.java
*/
