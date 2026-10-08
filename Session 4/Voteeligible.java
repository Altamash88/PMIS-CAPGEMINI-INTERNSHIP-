import java.util.*;
public class Voteeligible {
 public static void main(String[] args) {
    Scanner Sc = new Scanner(System.in);
            System.out.print("Enter the age : ");
            int age = Sc.nextInt();
            eligible(age);
 }   
 public static void eligible(int age){
    if(age >= 18){
       System.out.println("You are Eligible for the vote  ");
    }
    else{
       System.out.println("You are not Eligible for the vote  ");

    }
 }
}
