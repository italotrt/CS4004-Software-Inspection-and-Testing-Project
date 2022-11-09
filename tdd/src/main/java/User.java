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

    public User(String name, int age, String course, String department, boolean passedCaptcha, String university, String phoneNumber, String passEncrypted){
        if(passedCaptcha){
        this.name = name;
        this.age = age;
        this.course = course;
        this.department = department;
        this.passedCaptcha = passedCaptcha;
        this.university = university;
        this.phoneNumber = phoneNumber;
        this.passEncrypted = passEncrypted;
    }else{
            System.out.println("Captcha failed. Account could not be created");
        }
    }
    public void rentExternalBook(Book book){
        if(Objects.equals(book.uniOfOrigin, university)){
            rentedBooks.add(book);
        }else{
            System.out.println("you don't have permission!");
        }

    }
    public String toString(){
        StringBuilder s  = new StringBuilder();
        s.append(String.format("Name: %s %nAge: %d %nCourse: %s %nDepartment: %s %nPhone Number: %s %nUniversity: %s", name, age, course, department, phoneNumber, university));
        return s.toString();
    }
}
