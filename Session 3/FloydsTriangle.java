package Day3;

import java.util.Scanner;

public class FloydsTriangle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter row no");
        int a = sc.nextInt();

        int c = 1;

        for (int i = 1; i <= a; i++) {

            for (int j = 1; j <= i; j++) {

                System.out.print(c + " ");
                c++;
            }

            System.out.println();
        }
    }
}