package Wrapper;

public class Exercise1 {
    public static void main(String[] args) {
        String input = "10,20,30,40";
        String[] part = input.split(","); // "10 20 30 40"
        for (String string : part) {
            System.out.println(string);
        }

        int total = 0;

        for (String string : part) {
            total=total + Integer.parseInt(string); // 10+20+30

        }
        System.out.println("Total sum "+total);
    }
}
