package pkg;

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

    public Journal(String name) {
        this.name = name;
    }

    public Journal(String n, int min, boolean access){
        name = n;
        minimumWithdrawals = min;
        externalAccess = access;
    }

    public Journal(String name, String subject, String department, boolean available, int minimumWithdrawals, boolean externalAccess){
        this.name = name;
        this.subject = subject;
        this.department = department;
        this.available = available;
        this.minimumWithdrawals = minimumWithdrawals;
        this.externalAccess = externalAccess;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDepartment(String department) {
        this.department = department;
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


    public int getMinimumWithdrawals() {
        return minimumWithdrawals;
    }

    public boolean cancelSubscription(int withdrawals) {

        if (withdrawals < getMinimumWithdrawals() || isExternalAccess()) {
            setSubscriptionStatus(false);
            return false;
        } else {
            setSubscriptionStatus(true);
            return true;
        }
    }

    public void setMinimumWithdrawals(int i) {
        minimumWithdrawals = i;
    }

    public String getSubject() {
        return subject;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isAvailable() {
        return available;
    }
}

