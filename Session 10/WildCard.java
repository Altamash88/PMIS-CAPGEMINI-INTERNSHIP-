//Wild Card 
import java.util.*;
class Wild{
    public static void printList(List<?> list){
        for (Object o : list) {
            System.out.println(o);
        }

    }
}


public class WildCard {
    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(100,200,300);
        List<String> st = Arrays.asList("Alex","elen","lolo");
        List<Double> dl  = Arrays.asList(100.00,20.52);
        System.out.println("Interger: " +li );

    }
}
