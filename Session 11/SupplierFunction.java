import java.util.function.Supplier;

/**
 * SupplierFunction
 */
public class SupplierFunction {
    public static void main(String[] args) {
        Supplier<String> message = ()-> "This is Supplier Function";
        System.out.println(message.get());
    }
}