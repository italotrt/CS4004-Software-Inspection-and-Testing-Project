package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    static Library uwon;
    static Department historyDept;
    static Department compSciDept;
    static Department physicsDept;
    static Department mathsDept;
    static Book a;
    static Book b;
    static Book c;

    @BeforeAll
    public static void setUp() {
        uwon = new Library(true);
        a = new Book("Introduction to Sahir Sharma", "Idiots in computer science", 14, "A1");
        b = new Book("Maths and stuff!", "Maths", 14, "A1");
        c = new Book("Biology", "Biology", 21, "A1");
        uwon.addBook(a);
        uwon.addBook(b);
        uwon.addBook(c);
        historyDept = new Department("History");
        compSciDept = new Department("Computer Science");
        physicsDept = new Department("Physics");
        mathsDept = new Department("Mathematics");


    }

    @Test
    void testForUnnecessaryDuplicateBookAcquisition() {
        mathsDept.rentBook(a);
        compSciDept.rentBook(a);


        assertFalse(compSciDept.getCurrentRentedBooks().contains(a));
    }

    @Test
    void testThatBooksShowPreviousOwnersCorrectly() {

        historyDept.rentBook(b);
        int index = b.getPreviousOwners().size() - 1;

        assertEquals(b.getPreviousOwners().get(index), historyDept.getName());
    }

    @Test
    void testThatBooksAreRentedCorrectly() {
        physicsDept.rentBook(c);
        assertTrue(physicsDept.getCurrentRentedBooks().contains(c));

    }

    @Test
    void testCorrectUserRegistration() {
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");
        assertAll("User Details",
                () -> assertEquals("Mark Harrison", user.name),
                () -> assertEquals(19, user.age),
                () -> assertEquals("LM051", user.course),
                () -> assertEquals("Computer Science", user.department),
                () -> assertTrue(user.passedCaptcha),
                () -> assertEquals("University of Limerick", user.university),
                () -> assertEquals("0852585742", user.phoneNumber),
                () -> assertEquals("avcafai3", user.passEncrypted));
    }

    @Test
    void testCorrectStaffRegistration() {
        Staff staff = new Staff("John Boyne", "LM055");
        assertAll("User Details",
                () -> assertEquals("John Boyne", staff.name),
                () -> assertEquals("LM055", staff.course));

    }

    @Test
    void testUserCannotWithdrawBookWithoutPerms() {
        User user = new User("Canada Harrison", 19, "LM051", "Computer Science", true, "University of Ottawa", "0852585742", "avcafai3");
        User user2 = new User("Ireland Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");

        Book canadaBook = new Book("Canada Rivers and Lakes", "Geography", "University of Ottawa", "A1");
        Book canadaBook2 = new Book("Canada Mountains and Hills", "Geography", "University of Ottawa", "A1");

        user.rentExternalBook(canadaBook);
        user.rentExternalBook(canadaBook2);
        assertAll("Can book be withdrawn",
                () -> assertTrue(user.rentedBooks.contains(canadaBook)),
                () -> assertFalse(user2.rentedBooks.contains(canadaBook2)));
    }

    //Testing for unnecessary subscriptions to journals with limited interest || access is available via external universities
    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsTrue() {
        Journal j = new Journal(1, true);
        assertFalse(j.cancelSubscription(5));
    }

    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsFalse() {
        Journal j = new Journal(1, false);
        assertTrue(j.cancelSubscription(0));
    }

    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsFalse() {
        Journal j = new Journal(1, false);
        assertFalse(j.cancelSubscription(5000));
    }

    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsTrue() {
        Journal j = new Journal(600, true);
        assertFalse(j.cancelSubscription(24));
    }


    @Test
    void UserCanSendStaffEmail() {
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");
        Staff staff = new Staff("Nicholas O Mahony", "LM051");
        user.sendToStaff(staff, "WHAZZAP!!1");
        assertTrue(staff.inbox.contains("WHAZZAP!!1"));
    }

    @Test
    void staffCanSendUserEmail() {
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");
        Staff staff = new Staff("Nicholas O Mahony", "LM051");
        user.sendToStaff(staff, "I am a homosexual");
        assertTrue(staff.inbox.contains("I am a homosexual"));
    }

    @Test
    void testIfLoanTimeIsAccurate() {
        Book book = new Book("How to Analytics", "Statistics", 3, "A1");
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");
        user.rentExternalBook(book);
        user.rentExternalBook(a);
        user.rentExternalBook(b);
        assertAll("Rented Book Loan Times",
                () -> assertTrue(user.loanedBooks.get(0).equals("How to Analytics: 3 days left on loan.")),
                () -> assertTrue(user.loanedBooks.get(1).equals("Introduction to Sahir Sharma: 14 days left on loan.")),
                () -> assertTrue(user.loanedBooks.get(2).equals("Maths and stuff!: 14 days left on loan."))
        );

    }


    @Test
    void bookHasValidDepartment() {
        Book book = new Book("Pizza: A Villain Origin Story", "Religion", 3, "A1");
        assertTrue(book.getDepartment().length() > 0);
    }

    @Test
    void testSearchSystem() throws SearchException {
        Book book = new Book("Pizza: A Villain Origin Story", "Religion", 3, "A1");
        Library a = new Library(true);
        a.addBook(book);
        assertEquals(a.searchBookByTitle("Pizza: a villain origin story"), book);

        SearchException thrown = assertThrows(SearchException.class, () -> {
            a.searchBookByTitle("woo");
        }, "Book not found!");
        assertTrue(thrown.getMessage().contains("Book not found!"));

    }

    @Test
    void testBudgetRestrictions(){
        Department d = new Department("D1");
        d.setBudget(8000);
        Book b =  new Book("Beans", "Bean Eating", "Bean University", "Bean department");
        b.setPrice(100);
        assertTrue(d.bookBudgetCheck(d.getBudget(), d.purchaseBooks(b, 60)));
    }

    @Test
    void testSearchIfBookIsAvailableWhenLibraryIsOpen() {
        uwon.setOpen(true);
        assertEquals(true, uwon.searchBook(a));
    }

    @Test
    void testSearchIfBookIsAvailableWhenLibraryIsClosed() {
        uwon.setOpen(false);
        assertEquals(false, uwon.searchBook(a));
    }
}
