import java.util.*;

/**
 * UseCase11ConcurrentBookingSimulation
 * Demonstrates thread-safe booking using synchronization
 *
 * @author Goutham
 * @version 11.0
 */

// Reservation
class Reservation11 {
    String guestName;
    String roomType;

    public Reservation11(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Inventory (THREAD SAFE)
class RoomInventory11 {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory11() {
        inventory.put("Single Room", 1); // only 1 to show race condition prevention
    }

    // synchronized critical section
    public synchronized void bookRoom(Reservation11 r) {

        int available = inventory.getOrDefault(r.roomType, 0);

        if (available > 0) {
            System.out.println(Thread.currentThread().getName() +
                    " BOOKED for " + r.guestName);

            inventory.put(r.roomType, available - 1);

        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED for " + r.guestName + " (No rooms)");
        }
    }
}

// Booking Task (Thread)
class BookingTask implements Runnable {

    private Queue<Reservation11> queue;
    private RoomInventory11 inventory;

    public BookingTask(Queue<Reservation11> queue, RoomInventory11 inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (true) {

            Reservation11 r;

            // synchronized queue access
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.poll();
            }

            // process booking
            inventory.bookRoom(r);
        }
    }
}

// Main
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 11.0 ");
        System.out.println("=================================");

        // Shared queue
        Queue<Reservation11> queue = new LinkedList<>();

        queue.add(new Reservation11("Goutham", "Single Room"));
        queue.add(new Reservation11("Arun", "Single Room"));
        queue.add(new Reservation11("Priya", "Single Room"));

        // Shared inventory
        RoomInventory11 inventory = new RoomInventory11();

        // Create threads
        Thread t1 = new Thread(new BookingTask(queue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingTask(queue, inventory), "Thread-2");

        // Start threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll bookings processed safely!");
    }
}