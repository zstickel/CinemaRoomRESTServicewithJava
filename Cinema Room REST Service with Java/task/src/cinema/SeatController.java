package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SeatController {
    OurCinema ourCinema = new OurCinema(9, 9);
    Stats stats = new Stats();
    @GetMapping("/seats")
    public OurCinema getAllSeats() {
        return ourCinema;

    }


    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(name = "password", required = false) String password) {
            Map<String, String> errorResponse = new HashMap<>();
            if (password!= null && password.equals("super_secret")) {
                return ResponseEntity.ok(stats);
            } else {
                errorResponse.put("error", "The password is wrong!");
                return ResponseEntity.status(401).body(errorResponse);
            }


        // Your code here

    }
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody SeatPurchase seatPurchase) {
        Map<String, String> errorResponse = new HashMap<>();
        TicketInfo ticketInfo;
        Seat seat = ourCinema.getSeat(seatPurchase.getRow(), seatPurchase.getColumn());
        if (seat != null) {
            if (seat.purchased){

                errorResponse.put("error", "The ticket has been already purchased!");
                return ResponseEntity.badRequest().body(errorResponse);
            }else{
                seat.purchaseSeat();
                stats.addPurchasedSeat(seat.getPrice());
                ticketInfo = new TicketInfo(seat.getToken(),seat.getRow(),seat.getColumn());
            }
        }else{

            errorResponse.put("error", "The number of a row or a column is out of bounds!");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return ResponseEntity.ok(ticketInfo);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, UUID> returnTicket) {
        Map<String, String> errorResponse = new HashMap<>();
        String key = returnTicket.keySet().iterator().next();
        Seat seat = ourCinema.returnTicket(returnTicket.get(key));
        if (seat != null){
            Ticket ticket = new Ticket(seat.getRow(), seat.getColumn());
            Map<String, Ticket> ticketMap = new HashMap<>();
            ticketMap.put("ticket",ticket);
            stats.refundSeat(seat.getPrice());
            return ResponseEntity.ok(ticketMap);
        }else{
            errorResponse.put("error", "Wrong token!");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}