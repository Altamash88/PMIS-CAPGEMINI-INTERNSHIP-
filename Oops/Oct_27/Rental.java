package Oct_27;

public class Rental {
    private final Vehicle vehicle;
    private final Customer customer;
    private final int days;


    public Rental(Vehicle vehicle,  Customer customer, int days){
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }
    public double getCost(){
        return vehicle.calculateRent(days);

    }
    @Override
    public String toString(){
        return String.format("%s rented %s for %d days = %.2f", customer.getName(), vehicle, days, getCost());
    }
}
