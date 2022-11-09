import java.util.ArrayList;

public class Journal {
    private boolean subscriptionStatus;
    private static int borrowTotal;
    private String name;
    private String subject;
    private String department;
    private boolean available = true;
    private boolean externalAccess;
    private ArrayList<String> previousOwners = new ArrayList<>();

    public Journal(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public boolean isSubscriptionStatus() {
        return subscriptionStatus;
    }

    public static void setBorrowTotal(int borrowTotal) {
        Journal.borrowTotal = borrowTotal;
    }

    public String getName() {
        return name;
    }

    public void setExternalAccess(boolean a) {
        externalAccess = a;
    }

    public boolean isExternalAccess() {
        return externalAccess;
    }

    public static int getBorrowTotal() {
        return borrowTotal;
    }

    public String getDepartment() {
        return department;
    }

    public String getSubject() {
        return subject;
    }
}