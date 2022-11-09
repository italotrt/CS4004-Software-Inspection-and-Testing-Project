
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
        void testUserCannotWithdrawBookWithoutPerms(){
            User user = new User("Canada Harrison", 19, "LM051", "Computer Science", true, "University of Ottawa", "0852585742", "avcafai3");
            User user2 = new User("Ireland Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");

            Book canadaBook = new Book("Canada Rivers and Lakes", "Geography", "University of Ottawa");
            Book canadaBook2 = new Book("Canada Mountains and Hills", "Geography", "University of Ottawa");

            user.rentExternalBook(canadaBook);
            user.rentExternalBook(canadaBook2);
            assertAll("Can book be withdrawn",
                    () -> assertTrue(user.rentedBooks.contains(canadaBook)),
                    () -> assertFalse(user2.rentedBooks.contains(canadaBook2)));
        }

        @Test
        void subscriptionTest1 () {
            Journal j = new Journal("a", "b");
            j.setBorrowTotal(0); // Borrow total below limit
            j.setExternalAccess(true);  // Another Uni has access to the journal
            assertFalse(j.isSubscriptionStatus());
        }
            @Test
            void subscriptionTest2 () {
                Journal j = new Journal("1", "2");
                j.setBorrowTotal(10); // Borrow total above limit
                j.setExternalAccess(false);  // Another Uni does not have access to the journal
                assertTrue(j.isSubscriptionStatus());
            }

            @Test
            void subscriptionTest3 () {
                Journal j = new Journal("Yeet", "Skrrt");
                j.setBorrowTotal(0); // Borrow total below limit
                j.setExternalAccess(false);  // Another Uni does not have access to the journal
                assertFalse(j.isSubscriptionStatus());
            }

            @Test
            void subscriptionTest4 () {
                Journal j = new Journal("Beep", "Boop");
                j.setBorrowTotal(10); // Borrow total below limit
                j.setExternalAccess(true);  // Another Uni has access to the journal
                assertTrue(j.isSubscriptionStatus());
            }

        @Test
        void testSearchIfBookIsAvailableWhenLibraryIsOpen() {
            Library uwon = new Library(true);
            Book bookA = new Book("Bugs","Biology");
            uwon.addBook(bookA);

            assertEquals("Book " + bookA.getName() + " is available.", uwon.searchBook(bookA));
        }
        @Test
        void UserCanSendStaffEmail(){
            User user = new User("Mark Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");
            Staff staff = new Staff("Nicholas O Mahony", "LM051");
            user.sendToStaff(staff, "WHAZZAP!!1");
            assertTrue(staff.inbox.contains("WHAZZAP!!1"));
        }
        @Test
        void staffCanSendUserEmail(){
            User user = new User("Mark Harrison", 19, "LM051", "Computer Science", true, "University of Limerick", "0852585742", "avcafai3");
            Staff staff = new Staff("Nicholas O Mahony", "LM051");
            user.sendToStaff(staff, "I am a homosexual");
            assertTrue(staff.inbox.contains("I am a homosexual"));
        }



        }
