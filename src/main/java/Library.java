public class Library {
    private int openingTime;
    private int closingtime;

    public Library(int open, int close) {
        this.openingTime = open;
        this.closingtime = close;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingtime() {
        return closingtime;
    }
}