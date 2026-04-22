import java.util.*;

/**
 * UseCase9ErrorHandlingValidation
 * Demonstrates validation + custom exception handling
 *
 * @author Goutham
 * @version 9.0
 */

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory with validation
class RoomInventory9 {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory9() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    // Validate room type
    public void validateRoomType(String type) throws InvalidBookingException {
        if (!inventory.containsKey(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }
    }

    // Check availability
    public void checkAvailability(String type) throws InvalidBookingException {
        if (inventory.get(type) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + type);
        }
    }

    // Book room safely
    public void bookRoom(String guest, String type) throws InvalidBookingException {

        validateRoomType(type);
        checkAvailability(type);

        // safe update
        inventory.put(type, inventory.get(type) - 1);

        System.out.println("Booking SUCCESS for " + guest + " → " + type);
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 9.0 ");
        System.out.println("=================================");

        RoomInventory9 inventory = new RoomInventory9();

        // Test cases
        try {
            inventory.bookRoom("Goutham", "Single Room"); // valid
            inventory.bookRoom("Arun", "Suite Room");    // invalid type
            inventory.bookRoom("Priya", "Double Room");  // valid
            inventory.bookRoom("Kiran", "Double Room");  // no availability
        } catch (InvalidBookingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nSystem still running safely!");
    }
}