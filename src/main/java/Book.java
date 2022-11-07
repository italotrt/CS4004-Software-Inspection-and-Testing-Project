import java.lang.reflect.Array;
import java.util.ArrayList;

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