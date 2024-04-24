package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat {
    int row;
    int column;
    int price;
    boolean purchased = false;

    UUID token;
    Seat(int row, int column){
        this.row = row;
        this.column = column;
        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public int getPrice() {
        return price;
    }
    public void purchaseSeat(){
        this.purchased = true;
        this.token = UUID.randomUUID();
    }

    @JsonIgnore
    public UUID getToken() {
        return token;
    }
}