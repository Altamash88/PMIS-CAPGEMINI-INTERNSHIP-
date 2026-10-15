import java.util.Scanner;

public class ProblemOnExceptionHand {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number ");
        int a = sc.nextInt();
        System.out.println("Enter the second number ");
        int b = sc.nextInt();
        try{
            int c = a/b;
            System.out.println("Result is : "+ c);
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}
