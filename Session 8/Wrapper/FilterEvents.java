package Wrapper;

import java.util.ArrayList;
import java.util.List;

public class FilterEvents {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(12) ;
        numbers.add(7) ;
        numbers.add(23) ;
        numbers.add(44) ;
        numbers.add(91) ;
        numbers.add(50) ;

        List<Integer> events = new ArrayList<>();
        for (Integer integer : numbers) {
            if(integer%2==0){
                events.add(integer);
            }
        }
        System.out.println("Even Number : "+events);
    }   
}
