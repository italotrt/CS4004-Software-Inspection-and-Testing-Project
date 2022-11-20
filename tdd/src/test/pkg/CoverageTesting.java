package pkg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Locale;
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
    static ByteArrayOutputStream output;
    static String pass;
    static String message;
    static Staff staff;
    static User user;
    static User user2;
    static Book aBook;
    static Book bBook;
    static Book cBook;
    static Book dBook;
    static Book eBook;
    static Library lib;

    @BeforeAll
    public static void setUp() {
        output = new ByteArrayOutputStream();

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
        user2 = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        aBook = new Book("test", "maths", 2, "A1");
        bBook = new Book("BompSci", "maths", 2, "A1");
        cBook = new Book("CompSci", "maths", 2, "A1");
        dBook = new Book("Banana book", "food", 2, "Trinity", "A1");
        eBook = new Book("Intellij > VScode", "computer", 2, "UL", "A1");

            lib = new Library(true);
    }


    //Coverage tests for Book class

    @Test
    void testIfExternalBookWithLoanIsConstructedProperly() {
        Book b = new Book("Italo", "Brazil", 6, "Sao Paolo", "Linguistics");
        assertAll("Book constructor details",
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



    //coverage tests for library class
    @Test
    void testSearchBook() throws SearchException {
        lib.addBook(aBook);
        assertTrue(lib.searchBook(aBook));
    }

    @Test
    void testSearchBookFail() throws SearchException {
        Book nonExist = new Book("no", "no", 2, "A1");
        lib.addBook(aBook);
        SearchException thrown = assertThrows(SearchException.class, () ->
            lib.searchBook(nonExist)
        );


    }

    @Test
    void libraryIsOpen() {
        Library newLib = new Library(false);
        assertFalse(newLib.isOpen());
        newLib.setOpen(true);
        assertTrue(newLib.isOpen());
    }

    @Test
    void testSearchBookByTitle() throws SearchException {
        lib.addBook(aBook);
        Book b = new Book("test", "maths", 2, "A1");
        assertAll("Search by Title",
                () ->   assertEquals(lib.searchBookByTitle("test"), aBook),
                () -> assertNotEquals(lib.searchBookByTitle("test"),b));

    }

    @Test
    void testGetBooksInLibrary() {
        lib.addBook(aBook);

        Book[] inLibrary = {aBook};

        assertEquals(lib.getBooksInLibrary().get(0), inLibrary[0]);

    }

    @Test
    void testIfBookIsReserved(){
        User a = new User("Mark", 19, "Lm121", "Computer Science", true, "University of Limerick", "0852260882", "fafiw3");
        Book b = new Book("no", "Yes", 2, "A2");
        b.setAvailable(false);
        a.reserveBook(b);
        assertAll("Is book reserved",
        () -> assertEquals(b.getReservedUser(), a),
        () -> assertTrue(b.isReserved())
    );
    }


    //Coverage tests for Journal class
    @Test
    void testJournalsConstructor() {
        Journal j = new Journal("Beans", "Stink", "Biology", true, 40, true);
        assertAll("Journal Constructor and getter Details",
                () -> assertInstanceOf(Journal.class, j),
                () -> assertEquals("Beans", j.getName()),
                () -> assertEquals("Stink", j.getSubject()),
                () -> assertTrue(j.isAvailable()),
                () -> assertEquals(40, j.getMinimumWithdrawals()),
                () -> assertEquals("Biology", j.getDepartment()),
                () -> assertTrue(j.isExternalAccess())
        );
    }

    @Test
    void testJournalSettersAndGetters() {
        Journal j = new Journal("Beans", "Stink", "Biology", true, 40, true);
        j.setAvailable(false);
        j.setDepartment("Food and Culinary Arts");
        j.setSubscriptionStatus(true);
        assertAll("Journal details",
                () -> assertFalse(j.isAvailable()),
                () -> assertEquals("Food and Culinary Arts", j.getDepartment()),
                () -> assertTrue(j.isSubscriptionStatus())
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

    @Test
    void testSetExternalAccess() {

        Journal j = new Journal("comp sci weekly");

        assertFalse(j.isExternalAccess());
        j.setExternalAccess(true);
        assertTrue(j.isExternalAccess());
    }

    @Test
    void testMinimumWithdraws() {
        Journal j = new Journal("comp sci weekly");
        j.setMinimumWithdrawals(1);
        assertEquals(j.getMinimumWithdrawals(), 1);

        j.setMinimumWithdrawals(5);
        assertEquals(j.getMinimumWithdrawals(), 5);


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
    void testBudgetsOfDepartments() {
        Department a = new Department("Culinary");
        a.setBudget(50);
        assertEquals(50, a.getBudget());
    }

    @Test
    void testPurchaseOfBooks() {
        Department a = new Department("Culinary");
        a.setBudget(50);
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        b.setPrice(20);
        a.purchaseBooks(b, 1);
        assertTrue(a.getDepartmentBooks().contains(b));
    }

    @Test
    void testRentBookPass() {
        Department compSci = new Department("compSciDept");
        aBook.setAvailable(true);
        compSci.rentBook(aBook);
        assertTrue(compSci.getCurrentRentedBooks().contains(aBook));
    }

    @Test
    void testRentBookFail() {
        Department compSci = new Department("compSciDept");
        aBook.setAvailable(false);
        compSci.rentBook(aBook);
        assertFalse(compSci.getCurrentRentedBooks().contains(aBook));
    }

    @Test
    void testGetCurrentSubscriptions() {
        Department compSci = new Department("CompSci");
        Journal j = new Journal("computers weekly");

        String[] currentJournals = {j.getName()};

        compSci.subscribe(j);

        assertArrayEquals(compSci.getCurrentSubscriptions().toArray(new String[0]), currentJournals);

    }

    @Test
    void testSubscribing() {
        Department compSci = new Department("CompSci");
        Journal j = new Journal("computers weekly");
        assertFalse(compSci.getCurrentSubscriptions().contains(j.getName()));
        compSci.subscribe(j);
        assertEquals(compSci.getCurrentSubscriptions().get(0), j.getName());

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

    @Test
    void testBookCannotBeReservedTwice(){
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        b.setAvailable(false);
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        user.reserveBook(b);
        User user2 = new User("a",1,"a","a",true,"a","2","ff");
        assertEquals("Book is already reserved", user2.reserveBook(b));
    }
    @Test
    void testAvailableBookCannotBeReserved(){
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        assertEquals("Book is available and thus cannot be reserved", user.reserveBook(b));

    }
    @Test
    void testIfBookStatesAreRecordedCorrectlyIfUserDoesntHaveBook(){
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
            assertFalse(user.returnedBookState(b, true, true));

    }


    @Test
    void testIfSystemSendsNotificationToUserIfReservedBookAvailable(){
        String expected = String.format("Number %s: %s", phoneNo, "Your reserved book is available");


        Book b = new Book("Vegetables", "Cooking", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        User user2 = new User(name, age, course, depart, pCaptcha, uni, phoneNo, "fawf3");
        user.rentBook(b);
        user2.reserveBook(b);
        user.returnedBookState(b, true, false);

        assertEquals(expected,user2.messages.get(0));

    }
    @Test
    void testIfBookStatesAreRecordedCorrectlyIfUserDoesntReturnBook(){
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        user.rentBook(b);
        user.returnedBookState(b, false, true);


        assertAll("is book in vairous arraylists",
                () ->  assertFalse(user.returnedBooks.contains(b)),
                () ->  assertTrue(user.damagedOrStolenBooks.contains(b)));

    }

    @Test
    void testIfBookStatesAreRecordedCorrectlyIfUserReturns(){
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        user.rentBook(b);
        user.returnedBookState(b, true, false);
        assertAll("is book in vairous arraylists",
                () ->  assertTrue(user.returnedBooks.contains(b)),
                () ->  assertFalse(user.damagedOrStolenBooks.contains(b)));
    };

    @Test
    void testIfBookStatesAreRecordedCorrectlyIfUserReturnsDamagedBook(){
        Book b = new Book("Vegetables", "Cooking", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, uni, phoneNo, pass);
        user.rentBook(b);
        user.returnedBookState(b, true, true);
        assertAll("is book in vairous arraylists",
                () ->  assertTrue(user.returnedBooks.contains(b)),
                () ->  assertTrue(user.damagedOrStolenBooks.contains(b)));
    };

    @Test
    void testUserSendToStaff() {
        user.sendToStaff(staff, message);
        assertTrue(staff.inbox.contains(message));
    }

    @Test
    void testUserSendMessage() {
        PrintStream expected = System.out.printf("Number %s: %s", phoneNo, message);
        assertEquals(expected, user.sendMessage(message));
    }

    @Test
    void testUserToString() {
        String toString = "Name: " + user.getName() + "\n" +
                "Age: " + user.getAge() + "\n" +
                "Course: " + user.getCourse() + "\n" +
                "Department: " + user.getDepartment() + "\n" +
                "Phone Number: " + user.getPhoneNumber() + "\n" +
                "University: " + user.getUniversity() + "\n";

        assertInstanceOf(String.class, user.toString());
        assertEquals(toString, user.toString());
    }
    @Test
    void testIfLoanTimesAreAccurate(){
        Book b = new Book("Vegetables", "Cooking",  4, "Canada", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, "Canada", phoneNo, pass);
        user.rentBook(b);
        assertEquals("Vegetables: 4 days left on loan.", user.getLoanTimes().get(0));

    }
    @Test
    void testUniPermsWIthBooks(){

        Book b = new Book("Vegetables", "Cooking",  4, "Canada", "Culinary");
        User user = new User(name, age, course, depart, pCaptcha, "America", phoneNo, pass);
        user.rentBook(b);
        assertEquals("you don't have permission to rent book: Vegetables", user.messages.get(0));

    }

    @Test
    void testUserRentBookIf() {

        int before = user.getLoanTimes().size();
        user.rentBook(aBook);
        int after = user.getLoanTimes().size();
        assertNotEquals(before, after);

    }

    @Test
    void testUserRentBookElseIf() {

        int before = user.getLoanTimes().size();
        user.rentBook(eBook);
        int after = user.getLoanTimes().size();
        assertNotEquals(before, after);
    }

    @Test
    void testUserRentBookElse() {

        int before = user.getLoanTimes().size();
        user.rentBook(dBook);
        int after = user.getLoanTimes().size();
        assertEquals(before, after);

    }

}