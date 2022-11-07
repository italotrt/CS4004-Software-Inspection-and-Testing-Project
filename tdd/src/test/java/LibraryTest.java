
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class LibraryTest {
        static Department historyDept;
        static Department compSciDept;
        static Department physicsDept;
        static Department mathsDept;
        static Book a;
        static Book b;
        static Book c;

        @BeforeAll
        public static void setUp() {
            a = new Book("Introduction to Sahir Sharma", "Idiots in computer science");
            b = new Book("Maths and stuff!", "Maths");
            c = new Book("Biology", "Biology");
            historyDept = new Department("History");
            compSciDept = new Department("Computer Science");
            physicsDept = new Department("Physics");
            mathsDept = new Department("Mathematics");


        }

        @Test
        void testForUnnecessaryDuplicateBookAcquisition(){
            mathsDept.rentBook(a);
            compSciDept.rentBook(a);


            assertFalse(compSciDept.getCurrentRentedBooks().contains(a));
        }

    @Test
    void testThatBooksShowPreviousOwnersCorrectly(){

    historyDept.rentBook(b);
    int index = b.getPreviousOwners().size() -1;

    assertEquals(b.getPreviousOwners().get(index), historyDept.getName());
}

@Test
    void testThatBooksAreRentedCorrectly(){
    physicsDept.rentBook(c);
    assertTrue(physicsDept.getCurrentRentedBooks().contains(c));

}
}
