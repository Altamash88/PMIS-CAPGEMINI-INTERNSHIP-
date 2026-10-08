package Day2;

import java.util.Scanner;

public class Addition {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter your first no");
        float a = sc.nextFloat();

        System.out.println("enter your second no");
        float b = sc.nextFloat();

        int c = (int) (a + b);

        System.out.println(a + " + " + b + " = " + c);
    }
}