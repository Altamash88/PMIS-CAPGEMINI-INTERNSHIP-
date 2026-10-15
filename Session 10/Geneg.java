class Box<T>{
    private T item;
        void set(T item){
        this.item=item;
    }
    T get(){
        return item;
    }
}
public class Geneg {
    public static void main(String[] args) {
        Box<String> s = new Box<>();
        s.set("Samsung");
        System.out.println("Mobile : " + s.get());
        Box<Integer> i = new Box<>();
        i.set(25000);
        System.out.println("Price : " + i.get());

    }
}
