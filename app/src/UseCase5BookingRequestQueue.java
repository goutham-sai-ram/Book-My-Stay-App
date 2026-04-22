import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 * Demonstrates booking requests using Queue (FIFO)
 *
 * @author Goutham
 * @version 5.0
 */

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Booking Queue
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added for " + reservation.guestName);
    }

    // Display queue
    public void showQueue() {
        System.out.println("\n--- Booking Requests (FIFO Order) ---");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 5.0 ");
        System.out.println("=================================");

        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Goutham", "Single Room"));
        bookingQueue.addRequest(new Reservation("Arun", "Double Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));

        // Show queue
        bookingQueue.showQueue();

        System.out.println("\nAll requests stored. Waiting for processing...");
    }
}
