import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testForUnnecessaryDuplicateBookAcquisition(){
        Book a = new Book("Introduction to Sahir Sharma", "Idiots in computer science");
        Department historyDept = new Department("History");
        Department compSciDept = new Department("Computer Science");
        historyDept.rentBook(a);
        compSciDept.rentBook(a);
        assertFalse(compSciDept.getCurrentRentedBooks().contains(a));
    }
}