package cinema;

public class Stats {
    int income = 0;
    int available = 81;
    int purchased = 0;

    public int getAvailable() {
        return available;
    }

    public int getIncome() {
        return income;
    }

    public int getPurchased() {
        return purchased;
    }
    public void addPurchasedSeat(int price) {
        this.purchased++;
        this.available--;
        this.income += price;
    }
    public void refundSeat(int price) {
        this.purchased--;
        this.available++;
        this.income -= price;
    }
}
