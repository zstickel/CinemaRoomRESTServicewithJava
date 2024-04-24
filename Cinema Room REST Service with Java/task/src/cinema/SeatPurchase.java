package cinema;

public class SeatPurchase {
    int row;
    int column;
    int price;
    public SeatPurchase() {
    }
    public SeatPurchase(int row, int column) {
        this.row = row;
        this.column = column;
        price = row <= 4 ? 10 : 8;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
        price = row <= 4 ? 10 : 8;
    }

    public int getPrice() {
        return price;
    }

    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
}
