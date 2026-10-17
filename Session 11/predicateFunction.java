/**
 * Predicate
 */
import java.util.*;
import java.util.function.*;
public class predicateFunction {

    public static void main(String[] args) {
        Predicate<Integer> isAdult = age -> age>=18;

        System.out.println(isAdult.test(20));
        System.out.println(isAdult.test(18  ));
    }
}