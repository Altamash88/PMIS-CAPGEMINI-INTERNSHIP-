public class Problemquestion1 {
    static String name;

    public static void main(String[] args) {
        name = "Alex";
        
        // Testing Car Rental
        Vehicles v1 = new Car(1, "sus", 400);
        v1.CalculateRent(6);
        
        // Testing Bike Rental (with > 7 days discount)
        Vehicles v2 = new Bike(2, "Ninja", 150);
        v2.CalculateRent(8);
    }
}

interface Rentable {
    void CalculateRent(int day);
    boolean isAvailable();
}

abstract class Vehicles implements Rentable {
    int id;
    String model;
    double Dailyrate;
    boolean availability;

    public Vehicles(int id, String model, double Dailyrate) {
        this.id = id;
        this.model = model;
        this.Dailyrate = Dailyrate;
        this.availability = true;
    }

    public boolean isAvailable() {
        return availability;
    }
}

class Car extends Vehicles {
    Car(int id, String model, double Dailyrate) {
        super(id, model, Dailyrate);
    }

    public void CalculateRent(int day) {
        double cost = Dailyrate * day;
        // FIXED: Added all missing format specifiers (%s, %d, %.2f)
        System.out.printf("%s rented Car %s (ID: %d) for %d days. Total price is: %.2f\n", 
                          Problemquestion1.name, model, id, day, cost);
    }
}

class Bike extends Vehicles {
    Bike(int id, String model, double Dailyrate) {
        super(id, model, Dailyrate);
    }

    public void CalculateRent(int day) {
        double cost = Dailyrate * day;
        
        if (day > 7) {
            cost = cost * 0.90; // Apply 10% discount
            System.out.printf("[Discount Applied] %s rented Bike %s (ID: %d) for %d days = %.2f\n", 
                              Problemquestion1.name, model, id, day, cost);
        } else {
            System.out.printf("%s rented Bike %s (ID: %d) for %d days = %.2f\n", 
                              Problemquestion1.name, model, id, day, cost);
        }
    }
}
