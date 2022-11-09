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
    void testIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsTrue(){
        Journal j = new Journal(1, true);
        assertFalse(j.cancelSubscription(5));
    }

    @Test
    void testIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsFalse(){
        Journal j = new Journal(1, false);
        assertTrue(j.cancelSubscription(0));
    }

    @Test
    void testIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsFalse(){
        Journal j = new Journal(1, false);
        assertFalse(j.cancelSubscription(5000));
    }

    @Test
    void testIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsTrue(){
        Journal j = new Journal(600, true);
        assertFalse(j.cancelSubscription(24));
    }
        
    }
}
