@FunctionalInterface 
/**
 * InnerlamdaFunction
 */
interface Greeter {
    void greet(String name);
    
}
public class lamdaFunction {
    public static void main(String[] args) {
        Greeter g = name->{
        System.out.println("Hello : "+name);
        System.out.println("Hello World");
    };
    g.greet("Alex");
    }
}
