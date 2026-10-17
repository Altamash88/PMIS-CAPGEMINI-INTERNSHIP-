import java.util.function.Function;

/**
 * InnerQuestion1
 */
@FunctionalInterface 
interface Multiplication {
    int sqr(int a,int b);
}


public class Question1 {
   public static void main(String[] args) {
        // Multiplication mul = (a, b) -> a * b;
        // System.out.println(mul.sqr(10,20));

        Function <Integer , Integer> square = n -> n * n;
        System.out.println(square.apply(10));
   } 
}
