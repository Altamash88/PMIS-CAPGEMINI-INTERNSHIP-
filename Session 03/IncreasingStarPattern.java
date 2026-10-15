package Day3;

import java.util.Scanner;

public class IncreasingStarPattern {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter your row");
        int a = sc.nextInt();

        for (int i = 1; i <= a; i++) {

            for (int j = 1; j <= i; j++) {

                System.out.print("*");
            }

            System.out.println();
        }
    }
}