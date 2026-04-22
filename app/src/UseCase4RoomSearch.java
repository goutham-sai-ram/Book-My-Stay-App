import java.util.HashMap;
import java.util.Map;

/**
 * UseCase4RoomSearch
 *
 * @author Goutham
 * @version 4.0
 */

// Room base class
abstract class Room4 {
    String type;
    int beds;
    double price;

    public Room4(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Room types
class SingleRoom4 extends Room4 {
    public SingleRoom4() {
        super("Single Room", 1, 2000);
    }
}

class DoubleRoom4 extends Room4 {
    public DoubleRoom4() {
        super("Double Room", 2, 3500);
    }
}

class SuiteRoom4 extends Room4 {
    public SuiteRoom4() {
        super("Suite Room", 3, 5000);
    }
}

// Inventory
class RoomInventory4 {
    private Map<String, Integer> inventory;

    public RoomInventory4() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

// Search Service
class RoomSearchService4 {

    public void searchAvailableRooms(RoomInventory4 inventory) {

        System.out.println("\n--- Available Rooms ---");

        for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {

            String type = entry.getKey();
            int available = entry.getValue();

            if (available > 0) {

                Room4 room;

                if (type.equals("Single Room")) {
                    room = new SingleRoom4();
                } else if (type.equals("Double Room")) {
                    room = new DoubleRoom4();
                } else {
                    room = new SuiteRoom4();
                }

                room.displayDetails();
                System.out.println("Available: " + available + "\n");
            }
        }
    }
}

// Main class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 4.0 ");
        System.out.println("=================================");

        RoomInventory4 inventory = new RoomInventory4();
        RoomSearchService4 search = new RoomSearchService4();

        search.searchAvailableRooms(inventory);

        System.out.println("Search completed!");
    }
}