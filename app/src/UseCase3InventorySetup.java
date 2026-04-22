import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 * Demonstrates centralized inventory using HashMap
 *
 * @author Goutham
 * @version 3.1
 */

// Inventory class
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor → initialize data
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display all rooms
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 3.1 ");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating Single Room availability...");
        inventory.updateAvailability("Single Room", 4);

        // Show updated data
        inventory.displayInventory();

        System.out.println("\nApplication finished!");
    }
}