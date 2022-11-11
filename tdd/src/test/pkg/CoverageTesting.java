package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//testing strategy, cover every condition and method

public class CoverageTesting {

    static Book a;
    static Library b;

    @BeforeAll
    public static void setUp() {
        a = new Book("test", "maths", 2, "A1");
        b = new Library(true);
    }

    //coverage testing for each method and condition in Book

    @Test
    void testIfExternalBookIsConstructedProperly(){
    Book b = new Book("Italo", "Brazil", 6 ,"Sao Paolo", "Linguistics");
    assertAll("Book construcor details",
            () -> assertInstanceOf(Book.class, b),
            () -> assertEquals("Italo",b.getName()),
            () -> assertEquals("Brazil", b.getSubject()),
            () -> assertEquals(6, b.getLengthOfLoan()),
            () -> assertEquals("Sao Paolo", b.getUniOfOrigin()),
            () -> assertEquals("Linguistics", b.getDepartment())
                    );
    }

    @Test
    void testIfExternalBookWithLoanIsConstructedProperly(){
        Book b = new Book("Nick", "Gingers", 4, "Hair");
        assertAll("Book constructor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Nick",b.getName()),
                () -> assertEquals("Gingers", b.getSubject()),
                () -> assertEquals(4, b.getLengthOfLoan()),
                () -> assertEquals("Hair", b.getDepartment())
        );
    }
    @Test
    void testIfBookWithLoanIsConstructedProperly(){
        Book b = new Book("Nick", "Gingers", 4, "Hair");
        assertAll("Book constructor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Nick",b.getName()),
                () -> assertEquals("Gingers", b.getSubject()),
                () -> assertEquals(4, b.getLengthOfLoan()),
                () -> assertEquals("Hair", b.getDepartment())
        );
    }

    @Test
    void testIfExternalBookWithoutLoanIsConstructedProperly(){
        Book b = new Book("Sam", "Javascript", 3, "Ennis", "Losers");

        assertAll("Book constructor details",
                () -> assertInstanceOf(Book.class, b),
                () -> assertEquals("Sam",b.getName()),
                () -> assertEquals("Javascript", b.getSubject()),
                () -> assertEquals(3, b.getLengthOfLoan()),
                () -> assertEquals("Ennis", b.getUniOfOrigin()),
                () -> assertEquals("Losers", b.getDepartment())
        );
    }
    @Test
    void testSearchBook() {
        b.addBook(a);
        Book nonExist = new Book("no", "no", 2, "A1");
        assertTrue(b.searchBook(a));
        assertFalse(b.searchBook(nonExist));
    }

    @Test
    void libraryIsOpen() {
        Library newLib = new Library(false);
        assertFalse(newLib.isOpen());
        newLib.setOpen(true);
        assertTrue(newLib.isOpen());
    }

    @Test
    void testJournalGettersSettersConstructor(){
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

}