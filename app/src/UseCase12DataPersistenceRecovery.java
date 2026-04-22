import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery
 * Demonstrates saving and restoring system state
 *
 * @author Goutham
 * @version 12.0
 */

// Reservation (Serializable)
class Reservation12 implements Serializable {
    String guestName;
    String roomType;

    public Reservation12(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return guestName + " → " + roomType;
    }
}

// System State (Serializable)
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation12> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation12> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // SAVE data
    public void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("ERROR saving data: " + e.getMessage());
        }
    }

    // LOAD data
    public SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("Data loaded successfully!");
            return (SystemState) ois.readObject();

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh...");
            return null;
        }
    }
}

// Main
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 12.0 ");
        System.out.println("=================================");

        PersistenceService service = new PersistenceService();

        // Try loading existing data
        SystemState state = service.load();

        Map<String, Integer> inventory;
        List<Reservation12> bookings;

        if (state == null) {
            // First run → create data
            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);

            bookings = new ArrayList<>();
            bookings.add(new Reservation12("Goutham", "Single Room"));
            bookings.add(new Reservation12("Arun", "Double Room"));

            System.out.println("\nNew data created.");
        } else {
            // Load existing
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("\nRecovered Data:");
        }

        // Show data
        System.out.println("\nInventory: " + inventory);
        System.out.println("Bookings: " + bookings);

        // Save before exit
        service.save(new SystemState(inventory, bookings));

        System.out.println("\nSystem shutdown safely!");
    }
}