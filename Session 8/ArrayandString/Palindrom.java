package ArrayandString;

import java.util.Scanner;

public class Palindrom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Input : ");
        String inp = sc.nextLine().toLowerCase();
        String rev = "";

        for (int i=inp.length()-1;i>=0;i--) {
            rev+=inp.charAt(i);
        }
        if(inp.equals(rev)){
            System.out.println("Palindrome");
        }else{
            System.out.println("Not Palindrome");
        }
        
        
    }
}
