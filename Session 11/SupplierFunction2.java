import java.util.Random;
import java.util.function.Supplier;

public class SupplierFunction2 {
    public static void main(String[] args) {
        Supplier<String> otpgen = ()-> {
            Random random = new Random();
            int num = 1000 + random.nextInt(90000);
            return String.valueOf(num);

        };
        String userotp = otpgen.get();
        System.out.println("User otp is : " + userotp);
    }
}
