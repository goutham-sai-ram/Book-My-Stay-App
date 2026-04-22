import java.util.*;

/**
 * UseCase10BookingCancellation
 * Demonstrates booking cancellation + inventory rollback
 *
 * @author Goutham
 * @version 10.0
 */

// Reservation
class Reservation10 {
    String guestName;
    String roomType;
    String roomId;

    public Reservation10(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Inventory
class RoomInventory10 {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory10() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increaseRoom(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory: " + inventory);
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation10> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    // Add confirmed booking
    public void addBooking(Reservation10 r) {
        bookings.put(r.roomId, r);
        System.out.println("Booking confirmed: " + r.roomId);
    }

    // Cancel booking
    public void cancelBooking(String roomId, RoomInventory10 inventory) {

        if (!bookings.containsKey(roomId)) {
            System.out.println("ERROR: Booking not found for ID " + roomId);
            return;
        }

        Reservation10 r = bookings.get(roomId);

        // push to rollback stack
        rollbackStack.push(roomId);

        // restore inventory
        inventory.increaseRoom(r.roomType);

        // remove booking
        bookings.remove(roomId);

        System.out.println("Booking CANCELLED: " + roomId + " (" + r.roomType + ")");
    }

    // Show rollback stack
    public void showRollback() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

// Main
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 10.0 ");
        System.out.println("=================================");

        RoomInventory10 inventory = new RoomInventory10();
        CancellationService service = new CancellationService();

        // Simulate confirmed bookings
        service.addBooking(new Reservation10("Goutham", "Single Room", "SR-101"));
        service.addBooking(new Reservation10("Arun", "Double Room", "DR-201"));

        // Cancel one booking
        service.cancelBooking("SR-101", inventory);

        // Try invalid cancel
        service.cancelBooking("SR-999", inventory);

        // Show final state
        inventory.showInventory();
        service.showRollback();

        System.out.println("\nCancellation process completed!");
    }
}