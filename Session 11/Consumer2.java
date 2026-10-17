import java.util.function.Consumer;

public class Consumer2 {
    public static void main(String[] args) {
        Consumer<String> smsNotifier = mobNo ->{
            System.out.println("Sending OTP to the Phone No "+mobNo);
        };
        smsNotifier.accept("+91 2884784");
    }
}
