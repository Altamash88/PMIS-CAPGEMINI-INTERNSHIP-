import java.util.*;
public class Powercalculation {
    public static void main(String[] args) {
         Scanner Sc = new Scanner(System.in);
        System.out.print("Enter the value of base  : ");
        int a = Sc.nextInt();
        System.out.print("Enter second Exponent  : ");
        int b = Sc.nextInt();
        power(a,b);
    }
    public static void power(int a, int b){
        System.out.println("power of base to the exponent is : "+ Math.pow(a, b));
    }
}
