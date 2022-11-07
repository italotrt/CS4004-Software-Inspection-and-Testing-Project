import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    static Department historyDept;
    static Department compSciDept;
    static Department physicsDept;
    static Department mathsDept;
    static Book a;

    @BeforeAll
    static void setUp() {
        a = new Book("Introduction to Sahir Sharma", "Idiots in computer science");
        historyDept = new Department("History");
        compSciDept = new Department("Computer Science");
        physicsDept = new Department("Physics");
        mathsDept = new Department("Mathematics");
        historyDept.rentBook(a);
        compSciDept.rentBook(a);
    }

    @Test
    void testForUnnecessaryDuplicateBookAcquisition(){
        assertFalse(compSciDept.getCurrentRentedBooks().contains(a));
    }
}