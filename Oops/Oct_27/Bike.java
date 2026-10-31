package Oct_27;

public class Bike extends Vehicle {
    public Bike(String id, String model, double dailyRate){
        super(id, model, dailyRate);
    }
    @Override
    public double calculateRent(int days){
        double total = dailyRate * days;
        return days>=7 ? total * 0.9 : total;
    }
}
