package ArrayandString;

import java.util.Scanner;

public class reversearray {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("enter the size of the array : ");
        int n = sc.nextInt();
        int[] array = new int[n];
        System.out.println("Enter " + n + "  element : ");
        for(int i= 0;i<n;i++){
            array[i] = sc.nextInt();

        }
        int start = 0;
        int end = n-1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
        System.out.println("Reversed array : ");
        for(int i = 0; i<n;i++){
            System.out.println(array[i] + " ");
        }
        System.out.println();
    }
}
