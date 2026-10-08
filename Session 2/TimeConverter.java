package Day2;

import java.util.Scanner;

public class TimeConverter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter your seconds which you want to convert");
        int sec = sc.nextInt();

        int hr = sec / 3600;
        int min = (sec % 3600) / 60;
        int s = sec % 60;

        System.out.println(hr + "hr: " + min + "min: " + s + "sec ");
    }
}