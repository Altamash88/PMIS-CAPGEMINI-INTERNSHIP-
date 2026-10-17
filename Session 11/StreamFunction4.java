import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * StreamFunction4
 */
public class StreamFunction4 {

    public static void main(String[] args) {
        List<Integer> nums =List.of(3,5,4,7,8,9);
        List <String> words = List.of("Banana", "Fig", "Apple","Kiwi");
        List<String> byLength = words.stream()
        .sorted((a,b) ->a.length() -b.length())
        .collect(Collectors.toList());

        List<Integer> res =nums.stream()
        .sorted()
        .collect(Collectors.toList());

        System.out.println(res);
        System.out.println(byLength);
    }
}