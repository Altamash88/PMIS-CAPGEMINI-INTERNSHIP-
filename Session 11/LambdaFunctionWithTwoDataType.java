import java.util.function.*;
public class LambdaFunctionWithTwoDataType {
   public static void main(String[] args) {
        Function<String , Integer> getLength = name -> name.length();
        System.out.println(getLength.apply("Java"));
        System.out.println(getLength.apply("JavaCode"));
   } 
}
