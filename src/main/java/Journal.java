import java.util.ArrayList;

public class Journal {
    private boolean subscriptionStatus;
    private String name;
    private String subject;
    private String department;
    private boolean available = true;
    private int minimumWithdrawals;
    private boolean externalAccess;
    private ArrayList<String> previousOwners = new ArrayList<>();

    public Journal(int minimumWithdrawals, boolean externalAccess) {
        this.minimumWithdrawals = minimumWithdrawals;
        this.externalAccess = externalAccess;
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

    public String getName() {
        return name;
    }

    public void setExternalAccess(boolean a) {
        externalAccess = a;
    }

    public boolean isExternalAccess() {
        return externalAccess;
    }

    public String getDepartment() {
        return department;
    }

    public String getSubject() {
        return subject;
    }

    public int getMinimumWithdrawals() {
        return minimumWithdrawals;
    }

    public boolean cancelSubscription(int withdrawals) {
        if (getMinimumWithdrawals() >= withdrawals || isExternalAccess()) {
            subscriptionStatus = true;
            return true;
        }
        else {
            subscriptionStatus = false;
            return false;
        }
    }
}
