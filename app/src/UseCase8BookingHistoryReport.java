import java.util.*;

/**
 * UseCase8BookingHistoryReport
 * Demonstrates booking history & reporting
 *
 * @author Goutham
 * @version 8.0
 */

// Reservation class
class Reservation8 {
    String guestName;
    String roomType;
    String roomId;

    public Reservation8(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println("Guest: " + guestName +
                " | Room: " + roomType +
                " | Room ID: " + roomId);
    }
}

// Booking History
class BookingHistory {

    private List<Reservation8> history = new ArrayList<>();

    // Add booking
    public void addBooking(Reservation8 reservation) {
        history.add(reservation);
        System.out.println("Booking stored for " + reservation.guestName);
    }

    // Show all bookings
    public void showHistory() {
        System.out.println("\n--- Booking History ---");

        for (Reservation8 r : history) {
            r.display();
        }
    }

    // Simple report
    public void generateReport() {
        System.out.println("\n--- Booking Summary Report ---");
        System.out.println("Total Bookings: " + history.size());
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 8.0 ");
        System.out.println("=================================");

        BookingHistory history = new BookingHistory();

        // Simulating confirmed bookings (from UC6)
        history.addBooking(new Reservation8("Goutham", "Single Room", "SR-1001"));
        history.addBooking(new Reservation8("Arun", "Double Room", "DR-2001"));
        history.addBooking(new Reservation8("Priya", "Suite Room", "SU-3001"));

        // Show history
        history.showHistory();

        // Generate report
        history.generateReport();

        System.out.println("\nReporting completed!");
    }
}