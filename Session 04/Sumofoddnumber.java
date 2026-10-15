import java.util.Scanner;
public class Sumofoddnumber {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Enter the range from 1 to n: ");
        int n = Sc.nextInt();
        int sum = 0;
        Odd(sum,n);
    }
    public static void Odd(int sum,int n){
        for(int i = 1; i<=n;i++ ){
            if(i%2 != 0){
                sum+= i;
    
            }
            
        }
        System.out.println(sum);

    }
}
