package Day_5;

class Vehicle {

    String brand;

    void startEngine() {
        System.out.println(brand + " engine started.");
    }
}

class Bike extends Vehicle {

    boolean hasCarrier;

    void kickStand() {
        System.out.println("Kickstand put down.");
    }
}

public class Inheritance {

    public static void main(String[] args) {

        Bike myBike = new Bike();

        myBike.brand = "Shine";

        myBike.startEngine();

        myBike.kickStand();
    }
}