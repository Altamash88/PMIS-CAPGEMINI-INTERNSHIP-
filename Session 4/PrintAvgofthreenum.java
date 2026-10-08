import java.util.Scanner;
public class PrintAvgofthreenum {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("enter first num :");

    double a = sc.nextDouble();
    System.out.println("enter second num :");
    double b = sc.nextDouble();
    System.out.println("enter third num :");
    double  c = sc.nextDouble();
    Avg(a,b,c);
    
}
public static void Avg(double a, double b, double c){
    double ans = ( a+b+c)/ 3;
    System.out.println("Average:-  " + ans );
}
}