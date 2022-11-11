
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

        //Testing for unnecessary subscriptions to journals with limited interest || access is available via external universities
        @Test
        void TestIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsTrue(){
            Journal j = new Journal("a");
            j.setExternalAccess(true);
            j.setMinimumWithdrawals(1);
            assertFalse(j.cancelSubscription(5));
        }

        @Test
        void TestIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsFalse(){
            Journal j = new Journal("b");
            j.setExternalAccess(false);
            j.setMinimumWithdrawals(1);
            assertTrue(j.cancelSubscription(0));
        }

        @Test
        void TestIfSubscriptionCancelsIfWithdrawalsAboveLimitAndExternalAccessIsFalse(){
            Journal j = new Journal("c");
            j.setExternalAccess(false);
            j.setMinimumWithdrawals(1);
            assertFalse(j.cancelSubscription(5000));
        }

        @Test
        void TestIfSubscriptionCancelsIfWithdrawalsBelowLimitAndExternalAccessIsTrue(){
            Journal j = new Journal("d");
            j.setExternalAccess(true);
            j.setMinimumWithdrawals(600);
            assertFalse(j.cancelSubscription(24));
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

        @Test
        void functionalityOfJournalSubscriptionForDepartments(){
            Department d = new Department("d");
            d.subscribe(new Journal("Men's Digestives"));
            assertTrue(d.getCurrentSubscriptions().contains("Men's Digestives"));
        }

    }