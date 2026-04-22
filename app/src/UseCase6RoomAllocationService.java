import java.util.*;

/**
 * UseCase6RoomAllocationService
 * Demonstrates booking confirmation + safe allocation
 *
 * @author Goutham
 * @version 6.0
 */

// Reservation
class Reservation6 {
    String guestName;
    String roomType;

    public Reservation6(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory
class RoomInventory6 {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory6() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// Booking Service
class BookingService6 {

    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomAllocations = new HashMap<>();

    public void processBookings(Queue<Reservation6> queue, RoomInventory6 inventory) {

        System.out.println("\n--- Processing Bookings ---");

        while (!queue.isEmpty()) {

            Reservation6 r = queue.poll();
            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                String roomId = generateRoomId(type);

                // ensure uniqueness
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    roomAllocations
                            .computeIfAbsent(type, k -> new HashSet<>())
                            .add(roomId);

                    inventory.reduceRoom(type);

                    System.out.println("Booking CONFIRMED for " + r.guestName +
                            " | Room: " + type +
                            " | Room ID: " + roomId);

                }

            } else {
                System.out.println("Booking FAILED for " + r.guestName +
                        " | No rooms available for " + type);
            }
        }
    }

    private String generateRoomId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 4);
    }
}

// Main
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 6.0 ");
        System.out.println("=================================");

        // Queue (FIFO from UC5)
        Queue<Reservation6> queue = new LinkedList<>();

        queue.add(new Reservation6("Goutham", "Single Room"));
        queue.add(new Reservation6("Arun", "Single Room"));
        queue.add(new Reservation6("Priya", "Single Room")); // should fail

        queue.add(new Reservation6("Kiran", "Suite Room"));
        queue.add(new Reservation6("Rahul", "Suite Room")); // should fail

        // Inventory
        RoomInventory6 inventory = new RoomInventory6();

        // Booking service
        BookingService6 service = new BookingService6();

        // Process bookings
        service.processBookings(queue, inventory);

        System.out.println("\nAll bookings processed!");
    }
}