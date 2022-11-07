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
            System.out.println("Book is already rented");
        }
    }

}
