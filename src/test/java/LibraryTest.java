import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    static Library uwon;
    static Department historyDept;
    static Department compSciDept;
    static Department physicsDept;
    static Department mathsDept;
    static Book bookA;
    static Book bookB;
    static Book bookC;

    @BeforeAll
    static void setUp() {
        uwon = new Library(9, 21);
        bookA = new Book("Introduction to C", "Information Technology");
        bookB = new Book("Welcome to Vectors", "Maths");
        bookC = new Book("Bugs","Biology");
        historyDept = new Department("History");
        compSciDept = new Department("Computer Science");
        physicsDept = new Department("Physics");
        mathsDept = new Department("Mathematics");
        historyDept.rentBook(bookA);
        compSciDept.rentBook(bookA);
        historyDept.rentBook(bookB);
        physicsDept.rentBook(bookC);
    }

    @Test
    void testForUnnecessaryDuplicateBookAcquisition(){
        assertFalse(compSciDept.getCurrentRentedBooks().contains(bookA));
    }

    @Test
    void testThatBooksShowPreviousOwnersCorrectly(){
        int index = bookB.getPreviousOwners().size() -1;
        assertEquals(bookB.getPreviousOwners().get(index), historyDept.getName());
    }

    @Test
    void testThatBooksAreRentedCorrectly(){
        assertTrue(physicsDept.getCurrentRentedBooks().contains(bookC));
    }
}