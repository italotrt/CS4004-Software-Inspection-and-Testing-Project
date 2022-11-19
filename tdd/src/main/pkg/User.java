package pkg;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    String name;
    int age;
    String course;
    String department;
    boolean passedCaptcha;
    String university;
    String phoneNumber;
    String passEncrypted;
    ArrayList<Book> rentedBooks = new ArrayList<>();
    ArrayList<String> inbox = new ArrayList<>();
    ArrayList<String> loanedBooks = new ArrayList<>();
    ArrayList<Book> damagedOrStolenBooks = new ArrayList<>();
    ArrayList<Book> returnedBooks = new ArrayList<>();
    ArrayList<Book> reservedBooks = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();


    public User(String name, int age, String course, String department, boolean passedCaptcha, String university,
                String phoneNumber, String passEncrypted) {
        if (passedCaptcha) {
            this.name = name;
            this.age = age;
            this.course = course;
            this.department = department;
            this.passedCaptcha = passedCaptcha;
            this.university = university;
            this.phoneNumber = phoneNumber;
            this.passEncrypted = passEncrypted;
        } else {
            System.out.println("Captcha failed. Account could not be created");
        }
    }

    public void rentBook(Book book) {
        if (book.uniOfOrigin.equals("")) {
            rentedBooks.add(book);
            String s = book.getName() + ": " + book.getLengthOfLoan() + " days left on loan.";
            loanedBooks.add(s);
            book.setAvailable(false);
        } else if (Objects.equals(book.uniOfOrigin, university)) {
            rentedBooks.add(book);
            String s = book.getName() + ": " + book.getLengthOfLoan() + " days left on loan.";
            loanedBooks.add(s);
            book.setAvailable(false);
        } else {
            messages.add("you don't have permission to rent book: " + book.getName());
            System.out.println("you don't have permission to rent book: " + book.getName());
        }

    }

    boolean returnedBookState(Book b, boolean returned, boolean damaged) {
        if(!rentedBooks.contains(b)){
            System.out.println("You dont own that book!");
            return false;
        }
        if (returned && damaged) {
            returnedBooks.add(b);
            damagedOrStolenBooks.add(b);
            return true;
        } else if (returned) {
            returnedBooks.add(b);
            if (b.isReserved()) {
                b.getReservedUser().sendMessage("Your reserved book is available");
                b.setReserved(false);
                return true;
            } else {
                b.setAvailable(true);
                return true;
            }
        } else {
            damagedOrStolenBooks.add(b);
            return true;
        }
    }

    public void sendToStaff(Staff staff, String message) {
        staff.inbox.add(message);
    }

    public ArrayList<String> getLoanTimes() {
        return loanedBooks;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Course: " + course + "\n" +
                "Department: " + department + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "University: " + university + "\n";
    }

    public String reserveBook(Book book) {
        if (book.isReserved()) {
            return "Book is already reserved";
        } else if (book.getAvailable()) {
            return "Book is available and thus cannot be reserved";
        } else {
            book.setReserved(true);
            book.setReservedUser(this);
            return String.format("Book: %s has been reserved", book.getName());
        }
    }

    public PrintStream sendMessage(String message) {
        messages.add(String.format("Number %s: %s", phoneNumber, message));
        return System.out.printf("Number %s: %s", phoneNumber, message);
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }

    public boolean isPassedCaptcha() {
        return passedCaptcha;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUniversity() {
        return university;
    }

    public String getPassEncrypted() {
        return passEncrypted;
    }
}