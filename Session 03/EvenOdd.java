package Day3;

import java.util.Scanner;

public class EvenOdd {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter your first no");
        float a = sc.nextFloat();

        if (a == 0) {
            System.out.println("no is 0");
        } else if (a % 2 == 0) {
            System.out.println(a + " is even no");
        } else {
            System.out.println(a + " is odd no");
        }
    }
}