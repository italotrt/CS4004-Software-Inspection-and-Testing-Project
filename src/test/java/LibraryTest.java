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
        uwon = new Library(true);
        bookA = new Book("Introduction to C", "Information Technology");
        bookB = new Book("Welcome to Vectors", "Maths");
        bookC = new Book("Bugs","Biology");
        uwon.addBook(bookA);
        uwon.addBook(bookB);
        uwon.addBook(bookC);
        historyDept = new Department("History");
        compSciDept = new Department("Computer Science");
        physicsDept = new Department("Physics");
        mathsDept = new Department("Mathematics");
        //historyDept.rentBook(bookA);
        //compSciDept.rentBook(bookA);
        historyDept.rentBook(bookB);
        physicsDept.rentBook(bookC);
    }

    @Test
    void testForUnnecessaryDuplicateBookAcquisition() {
        assertFalse(compSciDept.getCurrentRentedBooks().contains(bookA));
    
    }

    @Test
    void testThatBooksShowPreviousOwnersCorrectly() {
        int index = bookB.getPreviousOwners().size() -1;
        assertEquals(bookB.getPreviousOwners().get(index), historyDept.getName());
    }

    @Test
    void testThatBooksAreRentedCorrectly() {
        assertTrue(physicsDept.getCurrentRentedBooks().contains(bookC));
    }

    @Test
    void testSearchIfBookIsAvailableWhenLibraryIsOpen() {
        uwon.setOpen(false);
        assertEquals("Book " + bookA.getName() + " is available.", uwon.searchBook(bookA));
    }

    void testForUnnecessaryDuplicateBookAcquisition(){
        Book a = new Book("Introduction to Sahir Sharma", "Idiots in computer science");
        Department historyDept = new Department("History");
        Department compSciDept = new Department("Computer Science");
        historyDept.rentBook(a);
        compSciDept.rentBook(a);
        assertFalse(compSciDept.getCurrentRentedBooks().contains(a));
        
    //Testing for unnecessary subscriptions to journals with limited interest || access is available via external universities 
    @Test
    void subscriptionTest1(){
        Journal j = new Journal("a", "b");
        j.setBorrowTotal(0); // Borrow total below limit
        j.setExternalAccess(true);  // Another Uni has access to the journal
        assertTrue(j.isSubscriptionStatus());
    @Test
    void subscriptionTest2(){
        Journal j = new Journal("1", "2");
        j.setBorrowTotal(10); // Borrow total above limit
        j.setExternalAccess(false);  // Another Uni does not have access to the journal
        assertTrue(j.isSubscriptionStatus());
    }

    @Test
    void subscriptionTest3(){
        Journal j = new Journal("Yeet", "Skrrt");
        j.setBorrowTotal(0); // Borrow total below limit
        j.setExternalAccess(false);  // Another Uni does not have access to the journal
        assertTrue(j.isSubscriptionStatus());
    }

    @Test
    void subscriptionTest4(){
        Journal j = new Journal("Beep", "Boop");
        j.setBorrowTotal(10); // Borrow total below limit
        j.setExternalAccess(true);  // Another Uni has access to the journal
        assertTrue(j.isSubscriptionStatus());
    }
        
    }
}
