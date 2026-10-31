package Oct_27;

import java.util.ArrayList;
import java.util.List;

public class RentalShop {
    private final List<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    public Rental rent(Customer customer, String vehicleId, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be positive");
        }

        Vehicle vehicle = findVehicle(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle " + vehicleId + " was not found");
        }
        if (!vehicle.isAvailable()) {
            throw new IllegalStateException(vehicle + " is already rented");
        }

        Rental rental = new Rental(vehicle, customer, days);
        vehicle.setAvailable(false);
        customer.addRental(rental);
        return rental;
    }

    public void giveBack(String vehicleId) {
        Vehicle vehicle = findVehicle(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle " + vehicleId + " was not found");
        }
        vehicle.setAvailable(true);
    }

    public void showAvailable() {
        System.out.println("Available vehicles:");
        for (Vehicle vehicle : fleet) {
            if (vehicle.isAvailable()) {
                System.out.println("  " + vehicle);
            }
        }
    }

    private Vehicle findVehicle(String vehicleId) {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

}
