package ArrayandString;

import java.util.Scanner;

public class CheckVowel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the String : ");
        String str = sc.nextLine().toLowerCase();

        int vowel = 0;
        int consonent = 0;
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);

            if(ch>='a' && ch <='z'){
                if(ch == 'a' ||ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u' ){
                    vowel++;
                }else{
                    consonent++;
                }
            }
        }
        System.out.println("Vowel : "+vowel);
        System.out.println("Consonents : "+consonent);
    } 
}
