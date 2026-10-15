class util{
    static <T> void twice(T value){
    System.out.println(value);
    System.out.println(value);

    }
    static <T> T firstof(T a, T b){
        return b;
    }
}
public class Gens2 {
    public static void main(String[] args) {
        util.twice(100);
        util.firstof(100, 100);
    }
}
