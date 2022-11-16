package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import java.lang.reflect.Array;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

//testing strategy, cover every condition and method
public class CoverageTesting {
    static String name;
    static String course;
    static int age;
    static String depart;
    static boolean pCaptcha;
    static String uni;
    static String phoneNo;
    static String pass;
    static String message;
    static Staff staff;
    static User user;
    static Book aBook;
    static Book bBook;
    static Book cBook;
    static Library lib;

    @BeforeAll
    public static void setUp() {
        name = "italo";
        course = "Comp Sci";
        age = 20;
        depart = "Testing";
        pCaptcha = true;
        uni = "UL";
        phoneNo = "123456789";
        pass = "password12345";
        message = "Hello World!";
        staff = new Staff(name, course);
        user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        aBook = new Book("test", "maths", 2, "A1");
        bBook = new Book("BompSci", "maths", 2, "A1");
        cBook = new Book("CompSci", "maths", 2, "A1");
        lib = new Library(true);
    }

    //Test to see if correct parameters have been passed
    @Test
    void testCorrectParameter() {
        assertAll("Correct parameters passed",
                () -> assertInstanceOf(String.class, name),
                () -> assertInstanceOf(String.class, course),
                () -> assertInstanceOf(Integer.class, age),
                () -> assertInstanceOf(String.class, depart),
                () -> assertInstanceOf(Boolean.class, pCaptcha),
                () -> assertInstanceOf(String.class, uni),
                () -> assertInstanceOf(String.class, phoneNo),
                () -> assertInstanceOf(String.class, pass)
        );
    }

    //Coverage tests for Book class
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

    //Coverage tests for Journal class
    @Test
    void testJournalsConstructor() {
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
    void testJournalSettersAndGetters(){
        Journal j = new Journal("Beans", "Stink", "Biology", true, 40, true);
        j.setAvailable(false);
        j.setDepartment("Food and Culinary Arts");
        j.setSubscriptionStatus(true);
        assertAll("Journal details",
                () -> assertFalse(j.isAvailable()),
                () -> assertEquals("Food and Culinary Arts", j.getDepartment()),
                () -> assertTrue( j.isSubscriptionStatus())
        );
    }

    @Test
    void testCancelSubscription() {
        Journal j = new Journal("j", 20, true);
        assertFalse(j.cancelSubscription(10));
        Journal j1 = new Journal("j1", 20, false);
        assertTrue(j1.cancelSubscription(20));
        Journal j2 = new Journal("j2", 20, false);
        assertFalse(j2.cancelSubscription(10));
    }

    //Coverage tests for Department class
    @Test
    void testDepartmentConstructor() {
        Department compSci = new Department(depart);

        assertAll("Department Constructor details",
                () -> assertInstanceOf(Department.class, compSci),
                () -> assertEquals(depart, compSci.getName())
        );
    }

    @Test
    void testBudgetsOfDepartments(){
        Department a = new Department("Culinary");
        a.setBudget(50);
        assertEquals(50, a.getBudget());
     }

     @Test
     void testPurchaseOfBooks(){
        Department a = new Department("Culinary");
        a.setBudget(50);
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        b.setPrice(20);
        a.purchaseBooks(b, 1);
        assertTrue(a.getDepartmentBooks().contains(b));
     }

    @Test
    void testRentBook() {
        Department compSci = new Department("compSciDept");
        compSci.rentBook(aBook);
        assertTrue(compSci.getCurrentRentedBooks().contains(aBook));
    }

    //Coverage tests for Staff class
    @Test
    void testStaffConstructor() {
        assertAll("Staff Constructor details",
                () -> assertInstanceOf(Staff.class, staff),
                () -> assertEquals(name, staff.getName()),
                () -> assertEquals(course, staff.getCourse())
        );
    }

    @Test
    void testStaffSendToUser() {
        staff.sendToUser(user, message);

        assertAll("Staff sendToUser method",
                () -> assertInstanceOf(User.class, user),
                () -> assertInstanceOf(String.class, message),
                () -> assertTrue(user.inbox.contains(message))
        );
    }

    //Coverage tests for User class
    @Test
    void testUserConstructorWithPassedCaptcha() {
        assertAll("User Constructor details",
                () -> assertInstanceOf(User.class, user),
                () -> assertEquals(name, user.getName()),
                () -> assertEquals(age, user.getAge()),
                () -> assertEquals(course, user.getCourse()),
                () -> assertEquals(depart, user.getDepartment()),
                () -> assertEquals(pCaptcha, user.isPassedCaptcha()),
                () -> assertEquals(phoneNo, user.getPhoneNumber()),
                () -> assertEquals(uni, user.getUniversity()),
                () -> assertEquals(pass, user.getPassEncrypted())
        );
    }

    @Test
    void testUserConstructorWithFailedCaptcha() {
        boolean pCaptcha = false;
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);

        assertAll("User Constructor details",
                () -> assertInstanceOf(Boolean.class, pCaptcha),
                () -> assertInstanceOf(User.class, user),
                () -> assertFalse(user.isPassedCaptcha())
        );
    }
}