package Oct_27;

public class rentalDemo {
    public static void main(String[] args){
        RentalShop shop = new RentalShop();
        shop.addVehicle(new Car("C1", "Swift", 200));
        shop.addVehicle(new Bike("B1", "Activa", 400));
        shop.addVehicle(new Truck("T1", "Tata Ace", 2500));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");

        System.out.println(shop.rent(asha, "B1", 8));
        System.out.println(shop.rent(ravi, "T1", 2));
        try {
            shop.rent(ravi, "B1", 1);
        } catch (IllegalStateException exception) {
            System.out.println("Error: " + exception.getMessage());
        }

        shop.giveBack("B1");
        shop.showAvailable();
    }


}
