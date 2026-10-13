package ArrayandString;

import java.util.Scanner;

public class largestandsmallest {
    public static void main(String[] args) {
        
        Scanner sc =new Scanner(System.in);
        System.out.println("enter the size of the array : ");
        int n = sc.nextInt();
        int[] array = new int[n];
        System.out.println("Enter " + n + "  element : ");
        for(int i= 0;i<n;i++){
            array[i] = sc.nextInt();

        }
        int min = array[0];
        int max = array[0];
        for(int i= 0;i<n;i++){
            if(array[i]>max){
                max = array[i];
            }
            if(array[i]<min){
                min = array[i];
            }
        }
            System.out.println("maximum : " + max);
            System.out.println("minimum : " + min);
        }
        }


