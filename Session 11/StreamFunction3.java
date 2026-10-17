import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamFunction3 {
    public static void main(String[] args) {

        //map: tranform each item

        List<Integer> nums = List.of(5,12,8,9,3,4,7);

        List<Integer> result =nums.stream()
        .map(n->n*2)
        .collect(Collectors.toList());
        
        System.out.println(result);
    }
}
