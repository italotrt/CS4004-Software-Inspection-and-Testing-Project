package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Array;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

//testing strategy, cover every condition and method
public class CoverageTesting {

    static Book aBook;
    static Book bBook;
    static Book cBook;
    static Library lib;
    static Department compSci;

    @BeforeAll
    public static void setUp() {
        aBook = new Book("test", "maths", 2, "A1");
        bBook = new Book("BompSci", "maths", 2, "A1");
        cBook = new Book("CompSci", "maths", 2, "A1");
        lib = new Library(true);
        compSci = new Department("compSciDept");
    }

    //coverage testing for each method and condition in Book
    @Test
    void testIfExternalBookWithLoanIsConstructedProperly() {
        Book b = new Book("Italo", "Brazil", 6, "Sao Paolo", "Linguistics");
        assertAll("Book construcor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Italo", b.getName()),
                () -> assertEquals("Brazil", b.getSubject()),
                () -> assertEquals(6, b.getLengthOfLoan()),
                () -> assertEquals("Sao Paolo", b.getUniOfOrigin()),
                () -> assertEquals("Linguistics", b.getDepartment())
        );
    }

    @Test
    void testIfBookWithoutLoanIsConstructedProperly() {
        Book b = new Book("Nick", "Gingers", "Hair");
        assertAll("Book constructor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Nick", b.getName()),
                () -> assertEquals("Gingers", b.getSubject()),
                () -> assertEquals("Hair", b.getDepartment())
        );
    }

    @Test
    void testIfBookWithLoanIsConstructedProperly() {
        Book b = new Book("Nick", "Gingers", 4, "Hair");
        assertAll("Book constructor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Nick", b.getName()),
                () -> assertEquals("Gingers", b.getSubject()),
                () -> assertEquals(4, b.getLengthOfLoan()),
                () -> assertEquals("Hair", b.getDepartment())
        );
    }

    @Test
    void testGetAndSetPrice() {
        Book b = new Book("Italo", "Brazil", 6, "Sao Paolo", "Linguistics");
        b.setPrice(10);
        assertEquals(10, b.getPrice());
    }

    @Test
    void testGetAndSetAvailable() {
        Book b = new Book("Italo", "Brazil", 6, "Sao Paolo", "Linguistics");
        b.setAvailable(false);
        assertFalse(b.getAvailable());
    }

    @Test
    void testAddAndGetPreviousOwners() {
        Book b = new Book("Italo", "Brazil", 6, "Sao Paolo", "Linguistics");
        User a = new User("Mark", 19, "Lm121", "Computer Science", true, "University of Limerick", "0852260882", "fafiw3");
        b.addToPreviousOwners(a.name);
        assertTrue(b.getPreviousOwners().contains(a.name));
    }


    @Test
    void testIfExternalBookWithoutLoanIsConstructedProperly() {
        Book b = new Book("Sam", "Javascript", "Ennis", "Losers");

        assertAll("Book constructor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Sam", b.getName()),
                () -> assertEquals("Javascript", b.getSubject()),
                () -> assertEquals("Ennis", b.getUniOfOrigin()),
                () -> assertEquals("Losers", b.getDepartment())
        );
    }

    @Test
    void testRemainingGetters() {

    }

    @Test
    void testSearchBook() {
        lib.addBook(aBook);
        Book nonExist = new Book("no", "no", 2, "A1");
        assertTrue(lib.searchBook(aBook));
        assertFalse(lib.searchBook(nonExist));
    }

    @Test
    void libraryIsOpen() {
        Library newLib = new Library(false);
        assertFalse(newLib.isOpen());
        newLib.setOpen(true);
        assertTrue(newLib.isOpen());
    }

    @Test
    void testJournalGettersSettersConstructor() {
        Journal j = new Journal("Beans", "Stink", "Biology", true, 40, true);
        assertAll("Journal Constructor and getter Details",
                () -> assertInstanceOf(Journal.class, j),
                () -> assertEquals("Beans", j.getName()),
                () -> assertEquals("Stink", j.getSubject()),
                () -> assertEquals(true, j.isAvailable()),
                () -> assertEquals(40, j.getMinimumWithdrawals()),
                () -> assertEquals("Biology", j.getDepartment()),
                () -> assertEquals(true, j.isExternalAccess())
        );
    }

    @Test
    void testCancelSubscription() {
        Journal j = new Journal("j", 20, true);
        assertFalse(j.cancelSubscription(10));
        Journal j1 = new Journal("j1", 20, false);
        assertFalse(j1.cancelSubscription(20));
        Journal j2 = new Journal("j2", 20, false);
        assertTrue(j2.cancelSubscription(10));
    }

    @Test
    void testUserConstructor(){
        User u = new User("Sam", 12, "LM051", "Losers", true, "UL", "0896969420", "il0v3j4va5cr1p7");

        assertAll("User Constructor details",
                () -> assertInstanceOf(User.class, u),
                () -> assertEquals("Sam", u.getName()),
                () -> assertEquals(12, u.getAge()),
                () -> assertEquals("LM051", u.getCourse()),
                () -> assertEquals("Losers", u.getDepartment()),
                () -> assertEquals(true, u.isPassedCaptcha()),
                () -> assertEquals("0896969420", u.getPhoneNumber()),
                () -> assertEquals("UL", u.getUniversity()),
                () -> assertEquals("il0v3j4va5cr1p7", u.getPassEncrypted())
        );
    }

    @Test
    void testDepartmentConstructor() {
        assertInstanceOf(Department.class, compSci);
    }

    @Test
    void testRentBook() {
        compSci.rentBook(aBook);
        Book[] books = new Book[]{aBook, bBook, cBook};
        cBook.setAvailable(false);

        assertAll(() -> assertTrue(compSci.getCurrentRentedBooks().contains(books[0])),
                  () -> assertFalse(compSci.getCurrentRentedBooks().contains(books[1])),
                  () -> assertLinesMatch((Stream<String>) compSci.getDepartmentBooks(), (Stream<String>) compSci.getCurrentRentedBooks())
        );
    }
}