package cinema;

import java.util.UUID;

public class TicketInfo {
    UUID token;
    Ticket ticket;
    TicketInfo(UUID token, int row, int column){
        this.token = token;
        this.ticket = new Ticket(row, column);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
