import java.util.*;
public class Checkcount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int poscount = 0;
        int negcount = 0;
        int zerocount = 0;
        while(true){
            System.out.print("Enter the number: ");
            int n = sc.nextInt();
            
            
            if(n>0){
                poscount++;
            }
            else if(n<0){
                negcount++;
            }
            else{
                zerocount++;
            }
            System.out.print("Enter the choice 1 for continue and 2 for exit the loop:  ");
             int choice = sc.nextInt();
             if(choice==2){
                break;
             }
                
            }
            System.out.println("Positive number : "+ poscount);
            System.out.println("Negative number : "+ negcount);
            System.out.println("Zero number : "+ zerocount);
            sc.close();

        }


    }

