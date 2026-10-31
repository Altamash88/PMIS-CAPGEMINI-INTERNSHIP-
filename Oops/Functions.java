package Oops;
import java.util.*;
/*
class test{
    public static void printMyName(String name){
        System.out.println("Your name is " + name);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        printMyName(name);
    }
}
*/

// Make a function to add 2 user input numbers and return the sum.
/* 
class test{
    public static float numbersAdd(float a, float b){
        float sum = a + b;
        return sum;
    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a: ");
        float a = sc.nextFloat();
        System.out.print("Enter b: ");
        float b = sc.nextFloat();
        System.out.printf("The sum is %.1f ",  numbersAdd(a,b));
       

    }
}
*/

// Make a function to multiply 2 user input numbers and return the product.
/* 
class test{
    public static float numbersProduct(float a, float b){
        return a*b;
    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("\nEnter a: ");
        float a = sc.nextFloat();
        System.out.print("Enter b: ");
        float b = sc.nextFloat();
        System.out.printf("\nThe product is %.1f ",  numbersProduct(a,b));
        }
        
       

    }
}
*/

// Print Factorial.
/*
class test{
    public static void printFactorial(int num){
        if(num <0){
            System.out.println("Invalid Input.");
            return;
        }
            

        int fact = 1;
        for(int i = num; i >= 1; i--){
            fact *= i;
        }
        System.out.print(fact +" !");
        return;

    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");

        int num = sc.nextInt();
        printFactorial(num);

    }
}
*/

// Fibonacci 
/*
class test{
    public static int fibonacci(int n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        return fibonacci(n-1) + fibonacci(n - 2);

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        for(int i = 0; i<= n; i++){
            System.out.print(fibonacci(i) + " ");
        }
    }
}
*/

// GCD
class Functions{

    public static int gcd(int a, int b){
        int gcd = 1;
        for(int i = 1; i<= a && i <= b; i++){
            if(a%i == 0 && b%i == 0){
                gcd = i;
            }
        }
    
    
        return gcd;

    }
    public static void main(String[] args){

    }

}