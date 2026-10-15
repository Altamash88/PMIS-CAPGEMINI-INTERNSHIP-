class Pair<K,V>{
    private K key;
    private V value;

    Pair(K key , V value){
        this.key=key;
        this.value=value;
    }

    K getKey(){
        return key;
    }
    V getValue(){
        return value;
    }
}

public class Gens {
    public static void main(String[] args) {
        Pair<String , Integer> pair = new Pair<>("Age" , 18);
        String k = pair.getKey();
        Integer v = pair.getValue();;
        System.out.println(k +" : "+ v);
    }
}
