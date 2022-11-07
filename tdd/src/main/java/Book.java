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


    public boolean getAvailable(){
        return available;
    }
    public ArrayList<String> getPreviousOwners(){
        return previousOwners;
    }
    public void setAvailable(boolean b){
        available = b;
    }
    public void addToPreviousOwners(String name){
        previousOwners.add(name);

    }

}
