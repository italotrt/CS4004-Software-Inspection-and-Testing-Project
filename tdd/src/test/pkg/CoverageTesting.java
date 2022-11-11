package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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