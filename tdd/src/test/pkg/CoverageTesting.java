package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoverageTesting {

    static Book a;
    static Library b;

    @BeforeAll
    public static void setUp() {
        a = new Book("test", "maths", 2, "A1");
        b = new Library(true);
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
}