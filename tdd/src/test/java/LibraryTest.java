import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
@Test
    void testThatBooksShowPreviousOwnersCorrectly(){
    Book a = new Book("A Game of Thrones", "Fantasy");
    Department litDept = new Department("Literature");
    litDept.rentBook(a);
    System.out.println(a.getPreviousOwners());
    int index = a.getPreviousOwners().size() -1;
    assertEquals(a.getPreviousOwners().get(index), litDept.getName());
}
@Test
    void testForUnnecessaryDuplicateBookAcquisition(){
    Book a = new Book("Introduction to Sahir Sharma", "Idiots in computer science");
    Department historyDept = new Department("History");
    Department compSciDept = new Department("Computer Science");

    historyDept.rentBook(a);
    compSciDept.rentBook(a);
    assertFalse(compSciDept.getCurrentRentedBooks().contains(a));

}
@Test
    void testThatBooksAreRentedCorrectly(){
    Book a = new Book("Butterfly", "Biology");
    Department bioDept = new Department("Biology");
    bioDept.rentBook(a);
    assertTrue(bioDept.getCurrentRentedBooks().contains(a));

}
}
