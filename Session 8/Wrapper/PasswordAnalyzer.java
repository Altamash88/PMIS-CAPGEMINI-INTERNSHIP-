package Wrapper;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        String text = "Pass@1234_Mumbai";
        int upper = 0 , lower = 0 , digit = 0, special=0;

        for (char ch : text.toCharArray()) {
            if(Character.isUpperCase(ch)){
                upper++;
            }else if(Character.isLowerCase(ch)){
                lower++;

            }else if(Character.isDigit(ch)){
                digit++;
            }else{
                special++;
            }
        }
        System.out.println("Uppercase: "+upper);
        System.out.println("Lowercase: "+lower);
        System.out.println("Digit: "+digit);
        System.out.println("special: "+special);
    }
}
