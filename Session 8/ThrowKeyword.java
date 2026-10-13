import java.util.Scanner;

public class ThrowKeyword {
    public static void main(String[] args) {
        System.out.println("Enter age : ");
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        if(age<=0){
            throw new IllegalArgumentException("Age Cannot be Nagative");
        }else{
            System.out.println("Age :  "+age);
        }
    } 
}
