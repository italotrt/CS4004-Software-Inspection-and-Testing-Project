import java.lang.reflect.Array;
import java.util.ArrayList;

public class Book {
    private String name;
    private String subject;
    private String department;
     String uniOfOrigin;
     private boolean available = true;
    private ArrayList<String> previousOwners = new ArrayList<>();
    public Book(String name, String subject){
        this.name = name;
        this.subject = subject;
    }
    public Book(String name, String subject, String uniOfOrigin){
        this.name = name;
        this.subject = subject;
        this.uniOfOrigin = uniOfOrigin;
    }



    public boolean getAvailable(){
        return available;
    }
    public String getName(){return name;}
    public ArrayList<String> getPreviousOwners(){
        return previousOwners;
    }
    public void setAvailable(boolean b){
        available = b;
    }
    public void addToPreviousOwners(String d){
        previousOwners.add(d);

    }

}
