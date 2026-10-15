class Locker<T>{
    private T item;

    void put(T item){
        this.item=item;
    }
    T get(){
        return item;
    }
}

public class LockerGen {
   public static void main(String[] args) {
        Locker<String> lock = new Locker<>();
        lock.put("C++ Book");
        System.out.println("The Locker has : "+lock.get());


        Locker<Integer> intLocker = new Locker<>();
        intLocker.put(1234);
        Locker<Double> doublePrice = new Locker<>();
        doublePrice.put(230.23);

        System.out.println("IntLocker has : "+intLocker.get());
        System.out.println("Lunch Price has : "+doublePrice.get());
   } 
}
