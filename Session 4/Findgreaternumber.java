import java.util.Scanner;
public class Findgreaternumber {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Enter first number : ");
        int a = Sc.nextInt();
        System.out.print("Enter second number : ");
        int b = Sc.nextInt();
        greater (a,b);
        
    }
    public static void  greater(int a, int b ){
        
        if(a > b){
            System.out.println("a is greater : " + a);
        }
        else{
            System.out.println("b is greater : " + b);
    
        } 

        }
}
