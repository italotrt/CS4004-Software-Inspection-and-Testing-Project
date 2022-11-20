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
        a = new Book("Introduction to CSIS", "Computer Science", 14, "A1");
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

    //TODO test this
    @Test
    void testThatReservationFeatureSetsTheBookToReserved() {
        User a = new User("Italo", 20, "Comp Sci", "CSIS", true, "UL", "1234", "pass");

        Book book = new Book("Java Programming", "Comp Sci", "CSIS");

        book.setAvailable(false);

        a.reserveBook(book);

        assertTrue(book.isReserved());

    }

    @Test
    void testThatReservedUserGetsBookNext() {
        User a = new User("Italo", 20, "Comp Sci", "CSIS", true, "UL", "1234", "pass");
        User b = new User("Sam", 18, "Comp Sci", "CSIS", true, "UL", "234", "sam23");

        Book book = new Book("Java Programming", "Comp Sci", "CSIS");

        a.rentBook(book);
        b.reserveBook(book);

        a.returnedBookState(book, true, false);

        assertFalse(book.getAvailable());
    }

    @Test
    void testThatBooksShowPreviousOwnersCorrectly() {
        b.setAvailable(true);
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
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");
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
        User user = new User("Canada Harrison", 19, "LM051", "Computer Science",
                true, "University of Ottawa", "0852585742", "avcafai3");
        User user2 = new User("Ireland Harrison", 19, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");

        Book canadaBook = new Book("Canada Rivers and Lakes", "Geography", "University of Ottawa", "A1");
        Book canadaBook2 = new Book("Canada Mountains and Hills", "Geography", "University of Ottawa", "A1");

        user.rentBook(canadaBook);
        user.rentBook(canadaBook2);
        assertAll("Can book be withdrawn",
                () -> assertTrue(user.rentedBooks.contains(canadaBook)),
                () -> assertFalse(user2.rentedBooks.contains(canadaBook2)));
    }

    //Testing for unnecessary subscriptions to journals with limited interest || access is available via external universities
    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsTrue() {
        Journal j = new Journal("a");
        j.setExternalAccess(true);
        j.setMinimumWithdrawals(1);
        assertFalse(j.cancelSubscription(5));
    }

    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsFalse() {
        Journal j = new Journal("b");
        j.setExternalAccess(false);
        j.setMinimumWithdrawals(1);
        assertFalse(j.cancelSubscription(0));
    }

    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsFalse() {
        Journal j = new Journal("c");
        j.setExternalAccess(false);
        j.setMinimumWithdrawals(1);
        assertTrue(j.cancelSubscription(5000));
    }

    @Test
    void TestIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsTrue() {
        Journal j = new Journal("d");
        j.setExternalAccess(true);
        j.setMinimumWithdrawals(600);
        assertFalse(j.cancelSubscription(24));
    }

    @Test
    void reserveABookThatIsNotAvailable(){
        Book b = new Book("Italo", "Brazil", "A2");
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");
        assertEquals("Book is available and thus cannot be reserved", user.reserveBook(b));

        b.setAvailable(false);
        User user2 = new User("Mark Harrison", 18, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");

        assertEquals("Book: Italo has been reserved", user.reserveBook(b));
        assertEquals("Book is already reserved", user2.reserveBook(b));
    }
    @Test
    void UserCanSendStaffEmail() {
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");
        Staff staff = new Staff("Nicholas O Mahony", "LM051");
        user.sendToStaff(staff, "WHAZZAP!!1");
        assertTrue(staff.inbox.contains("WHAZZAP!!1"));
    }

    @Test
    void staffCanSendUserEmail() {
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");
        Staff staff = new Staff("Nicholas O Mahony", "LM051");
        staff.sendToUser(user, "Hey!");
        assertTrue(user.inbox.contains("Hey!"));
    }

    @Test
    void testIfLoanTimeIsAccurate() {
        Book book = new Book("How to Analytics", "Statistics", 3, "A1");
        User user = new User("Mark Harrison", 19, "LM051", "Computer Science",
                true, "University of Limerick", "0852585742", "avcafai3");
        user.rentBook(book);
        user.rentBook(a);
        user.rentBook(b);
        assertAll("Rented Book Loan Times",
                () -> assertTrue(user.loanedBooks.get(0).equals("How to Analytics: 3 days left on loan.")),
                () -> assertTrue(user.loanedBooks.get(1).equals("Introduction to CSIS: 14 days left on loan.")),
                () -> assertTrue(user.loanedBooks.get(2).equals("Maths and stuff!: 14 days left on loan."))
        );

    }

    @Test
    void bookHasValidDepartment() {
        Book book = new Book("Pizza: A Villain Origin Story", "Religion", 3, "A1");
        assertAll("Book is valid" ,
                () ->         assertEquals("A1",book.getDepartment()),
                () ->         assertEquals( "Pizza: A Villain Origin Story",book.getName()),
                () ->         assertEquals("Religion", book.getSubject()),
                () ->         assertEquals(3, book.getLengthOfLoan())
                        );
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
    void testSearchIfBookIsAvailableWhenLibraryIsOpen() {
        uwon.setOpen(true);

        assertAll(() -> assertTrue(uwon.searchBook(a)),
                  () -> assertTrue(uwon.searchBook(b)),
                  () -> assertTrue(uwon.searchBook(c))
        );
    }

    @Test
    void testSearchIfBookIsAvailableWhenLibraryIsClosed() throws SearchException {
        Library r = new Library(false);
        a.setAvailable(true);
        r.addBook(a);
        System.out.println(r.getBooksInLibrary().size());
        assertTrue(r.searchBook(a));
    }

    @Test
    void testBudgetRestrictions() {
        Department d = new Department("D1");
        d.setBudget(8000);
        Book b = new Book("Beans", "Bean Eating", "Bean University", "Bean department");
        b.setPrice(100);
        d.purchaseBooks(b, 90);
        assertFalse(d.getDepartmentBooks().contains(b));
    }

    @Test
    void functionalityOfJournalSubscriptionForDepartments() {
        Department d = new Department("d");
        d.subscribe(new Journal("Men's Digestives"));
        assertTrue(d.getCurrentSubscriptions().contains("Men's Digestives"));
    }

    @Test
    void userReturnedBook(){
        User u = new User( "Bob",20, "LM051", "CS", true,
                "UL", "1234", "12-10103i4920jf0n");
        Book b = new Book("CompSci for nerds", "CompSci", "UL", "CSIS;");
        u.rentBook(b);
        u.returnedBookState(b, true, false);
        assertTrue(u.returnedBooks.contains(b));
    }

    @Test
    void userStealsBook(){
        User u = new User( "Bob",20, "LM051", "CS", true,
                "UL", "1234", "12-10103i4920jf0n");
        Book b = new Book("CompSci for nerds", "CompSci", "UL", "CSIS;");
        u.rentBook(b);
        u.returnedBookState(b, false, false);
        assertTrue(u.damagedOrStolenBooks.contains(b));
    }

    @Test
    void trackUsersDamagesBook(){
        User u = new User( "Bob",20, "LM051", "CS", true,
                "UL", "1234", "12-10103i4920jf0n");
        Book b = new Book("CompSci for nerds", "CompSci", "UL", "CSIS;");
        u.rentBook(b);
        u.returnedBookState(b, true, true);
        assertTrue(u.damagedOrStolenBooks.contains(b));
    }
    
}