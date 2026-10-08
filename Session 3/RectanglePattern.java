package Day3;

import java.util.Scanner;

public class RectanglePattern {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter your row");
        int a = sc.nextInt();

        System.out.println("enter your column");
        int b = sc.nextInt();

        for (int i = 1; i <= a; i++) {

            for (int j = 1; j <= b; j++) {

                System.out.print("*");
            }

            System.out.println();
        }
    }
}