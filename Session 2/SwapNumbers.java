package Day2;

import java.util.Scanner;

public class SwapNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("enter first no");
        int a = sc.nextInt();
        System.out.println("enter second no");
        int b = sc.nextInt();

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println(a);
        System.out.println(b);
    }
}