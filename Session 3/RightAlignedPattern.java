package Day3;

import java.util.Scanner;

public class RightAlignedPattern {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter your row");
        int a = sc.nextInt();

        for (int i = 1; i <= a; i++) {

            for (int j = 1; j <= a; j++) {

                if (j <= (a - i)) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }

            System.out.println();
        }
    }
}