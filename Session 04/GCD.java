import java.util.Scanner;

public class GCD {
    public static void main(String[] args) {
       Scanner Sc = new Scanner(System.in);
        System.out.print("Enter first number : ");
        int a = Sc.nextInt();
        System.out.print("Enter second number : ");
        int b = Sc.nextInt();
        int n = 1;
        int temp = 0;
         for (int i = 0; i <= n; i++) {
                if(a%n==0 && b%n==0){
                    temp = n;
                }
                n++;
         }
         System.out.println(temp);
    }
    
}
