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
        
    //Testing for unnecessary subscriptions to journals with limited interest || access is available via external universities 
    @Test
    void subscriptionTest1(){
        Journal j = new Journal("a", "b");
        j.setBorrowTotal(0); // Borrow total below limit
        j.setExternalAccess(true);  // Another Uni has access to the journal
        assertTrue(j.isSubscriptionStatus());
    }

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
