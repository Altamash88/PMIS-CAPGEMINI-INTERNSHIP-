package Day3;
import java.util.Scanner;

public class areaCalculator {
    public static void main(String[] args) {

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("area calculator");
            System.out.println("Enter the operation you want to perform");
            System.out.println("1. triangle | 2. rectangle | 3. square");
            int n = sc.nextInt();
            float cons = 0.5f;
            switch (n) {
                case 1: {

                    System.out.println("enter the breadth of triangle");
                    int b = sc.nextInt();
                    System.out.println("Enter the height of the triangle");
                    int h = sc.nextInt();
                    float r = cons * b * h;
                    System.out.println("the area of triangle is " + " " + r);
                    break;
                }

                case 2: {
                    System.out.println("enter the length of rectangle");
                    int l = sc.nextInt();
                    System.out.println("Enter the breadth of the rectangle");
                    int br = sc.nextInt();
                    int rec = 2 * l * br;
                    System.out.println("the area of rectangle is " + " " + rec);
                    break;
                }

                case 3: {
                    System.out.println("enter the side 1 of square");
                    int a = sc.nextInt();
                    System.out.println("enter the side 2 of square");
                    int a1 = sc.nextInt();
                    int rs = a * a1;
                    System.out.println("the area of square is " + " " + rs);
                    break;

                }
                default:
                    System.out.println("not a valid input");
            }
        }while(true);
    }

}