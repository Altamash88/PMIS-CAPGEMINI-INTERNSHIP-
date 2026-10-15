package ArrayandString;

import java.util.Scanner;

public class Exception {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("enter the size of the array : ");
        int n = sc.nextInt();
        int[] array = new int[n];
        System.out.println("Enter " + n + "  element : ");
        for(int i= 0;i<n;i++){
            array[i] = sc.nextInt();

        }
        int sum = 0;
        for(int i=0;i<n;i++){
            sum +=array[i];

        }
        double average = (double) sum/n;
        System.out.println("Sum: "+ sum);
        System.out.println("Average : " + average);
    }
}
