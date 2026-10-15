import java.util.*;
public class Calcircumference {
    public static void main(String[] args) {
        
        Scanner Sc = new Scanner(System.in);
            System.out.print("Enter radius : ");
            int r = Sc.nextInt();
            circle(r);
        }
        public static void circle(int r){
        double circum = 2 * Math.PI * r;
         System.out.println("circumference of circle is  : " + circum);

    }

}
