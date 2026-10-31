package Oops;

// Parent Class (Superclass)
class Vehicle {
    // ttribute
    String brand;

    // method
    void startEngine() {
        System.out.println(brand + " engine started.");
    }
}

// Child Class (Subclass) inherits from Vehicle
class Bike extends Vehicle {
    boolean hasCarrier;

    void kickStand() {
        System.out.println("Kickstand put down.");
    }
}

public class inheritance {
    public static void main(String[] args) {
        Bike myBike = new Bike();
        myBike.brand = "shine";     // Inherited from Vehicle
        myBike.startEngine();        // Inherited from Vehicle
        myBike.kickStand();          // Bike's own method
    }
}
