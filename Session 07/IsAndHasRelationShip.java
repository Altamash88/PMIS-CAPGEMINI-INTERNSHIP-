/**
 * IsAndHasRelationShip
 */
class Engine{
    void start(){
        System.out.println("Car Engine is Start");
    }
}

class Car{
    private Engine engine;
    Car(){
        this.engine = new Engine();
    }
    void drive(){
        engine.start();
        System.out.println("Car is Running...");
    }

}

public class IsAndHasRelationShip {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}