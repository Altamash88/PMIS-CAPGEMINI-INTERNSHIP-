package Oct_27;

public abstract class Vehicle implements Rentable {
    private final String id;
    private final String model;
    protected final double dailyRate;
    private boolean available = true;

    public Vehicle(String id, String model, double dailyRate){
        this.id =id;
        this.model = model;
        this.dailyRate = dailyRate;

    }

    public String getId(){
        return id;
    }
    public String getModel(){
        return model;
    }
    public boolean isAvailable(){
        return available;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return getClass().getSimpleName() + " " + model + " (" + id + ")";
    }



}
