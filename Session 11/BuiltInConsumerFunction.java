import java.util.function.*;
public class BuiltInConsumerFunction {
    public static void main(String[] args) {
        Consumer<String> pr = message -> System.out.println(message);
        pr.accept("Hiiiiii");
    }
}
