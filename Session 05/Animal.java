package Day_5;

public class Animal{

    public void run(String name, int distance){
        System.out.println(name + " has run " + distance +" km");
    }
    public void eat(String name, String dish){
        System.out.println(name + " is Eating " + dish);
    }
    public static void main(String[] args) {
        Animal jambo = new Animal();
        jambo.run("Jambo", 5);
        jambo.eat("Jambo", "Sugarcane");

        Animal cat = new Animal();
        cat.run("Lucy", 2);
        cat.eat("Lucy", "Milk");
    }
    
}