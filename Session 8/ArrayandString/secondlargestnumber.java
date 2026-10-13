package ArrayandString;

import java.util.Scanner;

public class secondlargestnumber {
    public static void main(String[] args) {
         Scanner sc =new Scanner(System.in);
        System.out.println("enter the size of the array : ");
        int n = sc.nextInt();
        if(n<2){
            System.out.println("Array must contain at least 2 number ");
            return;
        }
        int[] array = new int[n];
        System.out.println("Enter " + n + "  element : ");
        for(int i= 0;i<n;i++){
            array[i] = sc.nextInt();

        }
        int largest = Integer.MIN_VALUE;
        int secondlargest = Integer.MIN_VALUE;
        for(int i = 0; i<n;i++){
            if(array[i]>largest){
                secondlargest = largest;
                largest = array[i];

            }else if(array[i]>secondlargest && array[i]!= largest ){
                secondlargest = array[i];

            }
        }
        System.out.println("Secondlargest : "+ secondlargest);
        System.out.println("largest: " + largest);
        if(secondlargest== Integer.MIN_VALUE){
            System.out.println("No second largest distinct element found");
        }
        if(largest== Integer.MIN_VALUE){
            System.out.println("No largest distinct element found");
        }
        
    }
}
