import java.util.*;

/**
 * UseCase7AddOnServiceSelection
 * Demonstrates add-on services for reservations
 *
 * @author Goutham
 * @version 7.0
 */

// Service class
class AddOnService {
    String name;
    double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Service Manager
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added " + service.name + " to Reservation " + reservationId);
    }

    // Calculate total cost
    public double calculateTotal(String reservationId) {

        List<AddOnService> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        double total = 0;

        for (AddOnService s : services) {
            total += s.price;
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        System.out.println("\n--- Services for Reservation " + reservationId + " ---");

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println(s.name + " - ₹" + s.price);
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotal(reservationId));
    }
}

// Main
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 7.0 ");
        System.out.println("=================================");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Example reservation ID (from UC6)
        String reservationId = "SR-1234";

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));
        manager.addService(reservationId, new AddOnService("Spa", 1500));

        // Show services + cost
        manager.displayServices(reservationId);

        System.out.println("\nAdd-on services processed!");
    }
}