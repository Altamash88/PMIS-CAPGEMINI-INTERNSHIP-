import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamFunction {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5,5);
        List<Integer> res = nums.stream()
        .filter(n-> n%2==0)
        .map(n->n*n)
        .collect(Collectors.toList());

        System.out.println(nums);
        System.out.println(res);
    }
}
