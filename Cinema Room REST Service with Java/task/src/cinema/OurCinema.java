package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OurCinema {
    int rows;
    int columns;
    List<Seat> seats;
    OurCinema(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.seats = new ArrayList<>();
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                Seat seat = new Seat(i+1, j+1);
                seats.add(seat);
            }
        }
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(int row, int column) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        return null;
    }
    public Seat returnTicket(UUID token){
        for (Seat seat : seats){
            if (seat.purchased && seat.getToken().equals(token)){
                seat.purchased = false;
                seat.token = null;
                return seat;
            }
        }
        return null;
    }

}